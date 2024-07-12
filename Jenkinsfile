timeout(30) {
         node("windows-agent") {
         echo "Download project"
      checkout scm: [
                     $class: "GitSCM",
                   branches:[[name: 'main']],
      userRemoteConfigs: [[
      credentialsId: 'ed5ae3ee-53b5-4361-93da-637b67c2a39f',
      url: 'git@gitlab.com:alexqa2/practictests.git'
      ]]
     ]
     labelledShell (label: 'Run tests', script: '''

     chmod +x gradlew
     ./gradlew clean ui_test
     ''')
     allure([
             includeProperties: true,
             jdk              : '',
             properties       : [],
             reportBuildPolicy: 'ALWAYS',
             results          : [[path: 'build/allure-results']]
          ])
}
}