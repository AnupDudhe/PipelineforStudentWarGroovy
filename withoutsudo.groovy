pipeline {
    agent {
        label 'simple'
    }
    stages {
        stage('installing maven') {
            steps {
                sh '''
                   echo "we are installing maven"
                    apt update
                    apt install maven -y
                   '''
            }
        }
        stage('clone git on /home/ubuntu/workspace/project') {
            steps {
              
                echo "git pulling from public repository"
                 git 'https://github.com/AnupDudhe/studentapp-ui.git'
                
            }
        }
        stage('Artifact creation') {
            steps {
                sh ''' mvn clean
                 mvn package'''
            }
        }
     //   stage('Configuring aws cli') {
        //    steps {
        //        sh '''
         //       sudo apt install awscli -y
         //       sudo aws s3 cp target/  s3://newbucketcbz/
         //       sudo aws s3 cp s3://newbucketcbz/NVanupDelete.pem /'''
         //   }
    //    }
         stage('Tomcat-installation') {
            steps {
                sh ''' wget https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.98/bin/apache-tomcat-8.5.98.zip
                       apt install unzip -y
                       unzip apache-tomcat-8.5.98.zip
                       cp target/studentapp-2.2-SNAPSHOT.war  apache-tomcat-8.5.98/webapps/student.war
                       bash apache-tomcat-8.5.98/bin/catalina.sh stop
                       bash apache-tomcat-8.5.98/bin/catalina.sh start
                    '''
                
            }
        }
    }
}

wget usage no need to install package 