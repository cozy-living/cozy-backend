# Cozy

### This is Cozy's backend repo. See [frontend repo](https://github.com/cozy-living/cozy).

## Development Instructions

1. Run `git clone git@github.com:cozy-living/cozy.git` to clone the project to your local machine. If you haven't set up
   the SSH key already, check out [this link](https://docs.github.com/en/authentication/connecting-to-github-with-ssh).
2. `cd` into the repo you just created, run `git checkout -b [your name]` to create your own branch. Next time you want
   to switch to a different branch, make sure you've committed all your local changes first, then
   run `git checkout [branch name]`.
3. After you have made some code changes on your branch, run `git add .`, `git commit -m "your commit message"`
   , `git push` to commit your change to your remote branch on GitHub. Note that you need to
   run `git push --set-upstream origin [your branch name]` **the first time** after you created your local branch. Only
   push codes to GitHub if you make sure that the app is not broken after your latest code change.
4. Regularly push your code to GitHub, and make pull requests from main branch to fetch latest changes.
5. Remember to manaully update your `application.properties` file, as it is gitignored and only exists locally.

Please refer to database entity diagram, API documentation, feature assignment in meeting notes, and TODOs in the Spring Boot project when developing, and use Postman to test your APIs before pushing to GitHub

### Links (private to team members)

1. [Google Drive](https://drive.google.com/drive/u/0/folders/1NSGcEO8dKaahjkDrxAf-ZgzIof-ZQ_W4)
2. [ERD](https://app.diagrams.net/#G1cGTd0oRyGkhQOIIeLI4RYASQOdp0Cfl6)
3. [API Documentation](https://docs.google.com/document/d/1LLQX0z0ZPF6sfsjUweT3RumIj0ZczjyTNE3MAvJYzzs/edit)
4. [Meeting Notes](https://docs.google.com/document/d/1nL4bcvQmQDJLTFW4MXks3bw_MohTKD96hatoA1upAeU/edit)
5. [PRD](https://docs.google.com/document/d/1zdTJFq294dYcr9dUOdOnD4Fx4tFR5DvIc9H20VWuZd8/edit)
6. [Postman](https://www.getpostman.com/collections/47625032c62264ee8e0f)