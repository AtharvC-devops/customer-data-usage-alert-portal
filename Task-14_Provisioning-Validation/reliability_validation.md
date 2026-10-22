# Provisioning and Reliability Validation

## Clean provisioning on Ubuntu WSL2 with Docker Desktop

This is the recommended route when using Docker Desktop. Enable Ubuntu under Docker Desktop **Settings → Resources → WSL Integration** first. The Ansible playbook defaults to `manage_docker_engine: false` so it uses the Docker Desktop daemon instead of installing a second engine inside WSL.

In Ubuntu, from the repository root, install Ansible and its collection if not already installed:

```bash
sudo apt-get update
sudo apt-get install -y pipx python3-venv python3-docker
pipx install --include-deps ansible
export PATH="$HOME/.local/bin:$PATH"
ansible-galaxy collection install -r Task-13_Config-Management/requirements.yml
```

Confirm that Docker Desktop is reachable before proceeding:

```bash
docker version
docker info
docker compose version
```

Both Client and Server sections should appear in `docker version`. Build the image on that same daemon, then run provisioning twice:

```bash
docker compose build portal
docker image inspect customer-usage-alert-portal:local
docker compose down
ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml --ask-become-pass
ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml --ask-become-pass
```

The `docker compose down` command is included above because both configurations use the same container name. Compose uses its named data volume; Ansible uses `/opt/customer-usage-alert-portal/data`, so they are separate demo deployments. The first Ansible run should create the required packages, operator, directories, and container. The second run should report `changed=0` (or no changed tasks). Then verify the service:

```bash
curl --fail http://localhost:8085/actuator/health
docker ps --filter name=customer-usage-alert-portal
docker logs --tail 50 customer-usage-alert-portal
```

The HTTP response should contain `"status":"UP"`, and `docker ps` should show the portal container. Save both complete Ansible outputs and these verification results as genuine evidence. If the `appuser` already exists, Ansible will reconcile its group membership; do not use this playbook on a production machine.

## Native Linux VM option

For a disposable native Ubuntu VM where this playbook should install Docker Engine, pass `-e manage_docker_engine=true`. Do not set this on Ubuntu using Docker Desktop integration. Make the image available to the VM daemon first (for example, build it on the VM or `docker pull` a registry image), then use the same playbook and verification commands.

## Rollback

Keep at least two immutable image tags available to the target daemon. The playbook does not pull automatically. Pull the desired tags before deploying a registry image, then deploy the newer tag through the playbook:

    docker pull YOUR-USERNAME/customer-usage-alert-portal:2
    ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml -e app_image=YOUR-USERNAME/customer-usage-alert-portal:2 --ask-become-pass

If the newer version is unhealthy, restore the previous known-good tag:

    docker pull YOUR-USERNAME/customer-usage-alert-portal:1
    ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml -e app_image=YOUR-USERNAME/customer-usage-alert-portal:1 --ask-become-pass

Run the health check after the rollback and save both playbook outputs plus the HTTP response. Use tags available in your own registry; the example tag names above are placeholders.

