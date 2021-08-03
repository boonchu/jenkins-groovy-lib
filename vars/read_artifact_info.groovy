#!groovy

def call() {
    def pom = readMavenPom file: 'pom.xml'
    env.ARTIFACT_VERSION = pom.getVersion()
    env.ARTIFACT_PKG_NAME = pom.getArtifactId()
    echo "CALL->INFO : ARTIFACT_VERSION is ${env.ARTIFACT_VERSION}"
    echo "CALL->INFO : ARTIFACT_PKG_NAME is ${env.ARTIFACT_PKG_NAME}"
    sh "echo ${pom.version} > .git/tagName"
    tagName = readFile('.git/tagName')
    echo "CALL->INFO: value read from file .git/tagName as ${tagName}"
    // set DisplayName
    currentBuild.displayName = tagName
}
