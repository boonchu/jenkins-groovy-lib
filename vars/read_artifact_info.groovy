#!groovy

def call() {
    def pom = readMavenPom file: 'pom.xml'
    echo "CALL->INFO : ARTIFACT_PKG_VERSION is ${pom.version}"
    sh "echo ${pom.version} > .git/tagName"
    tagName = readFile('.git/tagName')
    echo "CALL->INFO: value read from file .git/tagName as ${tagName}"
    // set DisplayName
    currentBuild.displayName = tagName
    return pom
}
