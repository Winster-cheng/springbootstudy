pipeline {
    agent {
        docker {
            image 'reg.haimaiche.net/library/workspace:1.0.2' 
            label 'jin'
            args '-v /root/.m2:/root/.m2 -v /root/.ssh:/root/.ssh -v /etc/ansible:/etc/ansible -v /var/opt/adminset/data:/var/opt/adminset/data -v /var/opt/adminset/logs:/var/opt/adminset/logs -v /var/run/docker.sock:/var/run/docker.sock -v /usr/bin/docker:/usr/bin/docker' 
        }
    }
    // environment {
    //     DISABLE_AUTH = 'true'
    // }
    stages {
      stage("Check"){
          steps{
              sh 'printenv'
          }
      }
      stage("Analysis") {
          
          when {
              expression { GIT_BRANCH =~ /sonar/ }
          }
          
          steps{
              withSonarQubeEnv('sonar') {
                 sh 'mvn sonar:sonar'
              }
              script{
                  timeout(time: 1, unit: 'HOURS') {
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
                  } 
              }
          }
      }
      stage('Test') {
          when {
              expression { GIT_BRANCH =~ /release/ }
          }
          steps {
              sh 'printenv'
          }
      }
        stage('Package') { 
            steps {
                sh 'cp "$SCRIPTS_PATH"/ansible/shell/package.sh ./package.sh; sh ./package.sh'
            }
        }
        stage('Deploy') { 
            steps {
                sh 'cp "$SCRIPTS_PATH"/ansible/shell/deploy.sh ./publish.sh; sh ./publish.sh'
                // sh 'ping baidu.com'
            }
        }
    }
    post {
        always {
            echo 'One way or another, I have finished'
            deleteDir() /* clean up our workspace */
        }
        success {
            echo 'I succeeeded!'
            emailext (
                subject: "'${env.JOB_NAME} [${env.BUILD_NUMBER}]' 更新正常",
                body: """
                详情：
                SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'
                状态：${env.JOB_NAME} jenkins 更新运行正常 
                URL ：${env.BUILD_URL}
                项目名称 ：${env.JOB_NAME} 
                项目更新进度：${env.BUILD_NUMBER}
                """,
                to: "baiqi@maihaoche.com",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                )
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            echo 'I failed :('
            emailext (
                subject: "'${env.JOB_NAME} [${env.BUILD_NUMBER}]' 更新失败",
                body: """
                详情：
                FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'             
                状态：${env.JOB_NAME} jenkins 运行失败 
                URL ：${env.BUILD_URL}
                项目名称 ：${env.JOB_NAME} 
                项目更新进度：${env.BUILD_NUMBER}
                """,
                to: "baiqi@maihaoche.com",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                )
        }
        changed {
            echo 'Things were different before...'
        }
    }
}
