# Provisioning and Reliability Validation

## Clean provisioning

1. Start with a disposable VM/WSL target and install Ansible plus the collection listed in Task-13_Config-Management/requirements.yml.
2. Build and tag the Docker image, then make that image available to the target. For a registry image, use a version tag such as 1.0. For a local-only image, remove pull: true from the playbook for the exercise.
3. Run the playbook once and save its complete output.
4. Run it again unchanged. Confirm the package, directory, service, and container tasks report no changes.
5. Check http://localhost:8085/actuator/health and verify the dashboard loads.

## Rollback

Keep at least two immutable image tags. Deploy a newer tag through the playbook:

    ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml -e app_image=YOUR-USERNAME/customer-usage-alert-portal:2

If the newer version is unhealthy, restore the previous known-good tag:

    ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml -e app_image=YOUR-USERNAME/customer-usage-alert-portal:1

Run the health check after the rollback and save both playbook outputs plus the HTTP response. Use tags available in your own registry; the example tag names above are placeholders.

