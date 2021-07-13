#!groovy

def call() {
    def pom = readMavenPom file: 'pom.xml'
    ARTIFACT_VERSION = pom.version
    ARTIFACT_PKG_NAME = pom.packaging
    echo "LOG->INFO : ARTIFACT_VERSION is ${ARTIFACT_VERSION}"
    echo "LOG->INFO : ARTIFACT_PKG_NAME is ${ARTIFACT_PKG_NAME}"
    sh "echo ${pom.version} > .git/tagName"
    tagName = readFile('.git/tagName')
    echo "LOG->INFO: Tag value read as ${tagName}"
    // set DisplayName
    currentBuild.displayName = tagName
}


def read_pom_file() {
    def pom = readMavenPom file: 'pom.xml'
    ARTIFACT_VERSION = pom.version
    ARTIFACT_PKG_NAME = pom.packaging
    echo "LOG->INFO : ARTIFACT_VERSION is ${ARTIFACT_VERSION}"
    echo "LOG->INFO : ARTIFACT_PKG_NAME is ${ARTIFACT_PKG_NAME}"
	return pom
}
