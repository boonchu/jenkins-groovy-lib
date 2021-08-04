#!groovy

def call() {
    def pom = readMavenPom file: 'pom.xml'
    sh "echo ${pom.version} > .git/tagName"
    tagName = readFile('.git/tagName')
    echo "CALL->INFO: value read from file .git/tagName as ${tagName}"
    // set DisplayName
    currentBuild.displayName = tagName
}
