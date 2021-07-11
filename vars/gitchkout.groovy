#!groovy

// https://gist.github.com/jubel-han/12a7f3c86d6e1b2a5124b24ccea60580

def call(String branch, String gitUrl) {
    branch =  branch ?: 'master'
    // cleanup
    gitClean()
    // checkout
    git branch: "${branch}", url: "${gitUrl}"
    // get last tag
    sh "git describe --abbrev=0 --tags > .git/tagName"
    tagName = readFile('.git/tagName')
    echo "${tagName}"
    // set DisplayName
    currentBuild.displayName = tagName
}


/*
 * Clean a Git project workspace.
 * Uses 'git clean' if there is a repository found.
 * Uses Pipeline 'deleteDir()' function if no .git directory is found.
 */
def gitClean() {
    timeout(time: 60, unit: 'SECONDS') {
        if (fileExists('.git')) {
            echo 'Found Git repository: using Git to clean the tree.'
            sh 'git reset --hard'
        }
        else
        {
            echo 'No Git repository found: using deleteDir() to wipe clean'
            // deleteDir()
        }
    }
}
