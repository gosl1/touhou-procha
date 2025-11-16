11/15/25

- left-shift + right click on folder to open terminal immediately


- subst P: "C:\Users\User\Documents\THEYRE ALL IN   HERE\coding-projects\TouhouProcha\touhou-procha"
    this now points to the project itself whenever i call " P: "

- This adds everything to the repository
    git add .
    git commit -m "All changes"
    git push

    change to git add file_name.java if you want to add individual files

- Stage vs Commit vs Push 
    Stage - prepares selected changed for the snapshot
    Commit - Commits snapshot locally
    Push - Pushes it into github for everyone


- pulling
    git pull origin <name of branch>
    for now, we might not be doing branches so
    git pull origin main