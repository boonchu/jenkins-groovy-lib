#!groovy

def call() {
    def pom = readMavenPom file: 'pom.xml'

    // https://maven.apache.org/pom.html
    env.ARTIFACT_VERSION = pom.getVersion()
    env.ARTIFACT_PKG_NAME = pom.getArtifactId()
    env.ARTIFACT_PKG_SUFFIX = pom.getPackaging()
  
    echo "CALL->INFO : ARTIFACT_VERSION is ${env.ARTIFACT_VERSION}"
    echo "CALL->INFO : ARTIFACT_PKG_NAME is ${env.ARTIFACT_PKG_NAME}"
    echo "CALL->INFO : ARTIFACT_PKG_SUFFIX is ${env.ARTIFACT_PKG_SUFFIX}"
    sh "echo ${pom.version} > .git/tagName"
    tagName = readFile('.git/tagName')
    echo "CALL->INFO: value read from file .git/tagName as ${tagName}"
    // set DisplayName
    currentBuild.displayName = tagName
}
