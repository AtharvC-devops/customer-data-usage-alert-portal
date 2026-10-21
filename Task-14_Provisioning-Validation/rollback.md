# Rollback Runbook

1. Record the last known-good image tag before deployment.
2. Deploy the new immutable image tag and check the health endpoint.
3. If the app fails health validation, redeploy the recorded good tag with the Ansible extra variable app_image.
4. Confirm the prior version answers at port 8085.
5. Record the failing deployment, rollback command/output, final health result, and release tag in the evidence folder.

The database is mounted separately from the image. Schema changes that cannot read old data require a separate migration/backup plan; this MVP does not contain destructive schema migrations.

