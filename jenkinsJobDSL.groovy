/* Jenkins Job DSL Demo Script */
 
def interfaces = ['foo-interfaceA', 'foo-interfaceB', 'foo-interfaceC', 'foo-interfaceD', 'foo-interfaceE', 'foo-interfaceF']
def release = 'R1812' 

interfaces.each{
  def interfaceName = "$release-$it"; 
  freeStyleJob(interfaceName){
    jdk('1.8')
    scm{
      svn{
	location("https://svn4.sliksvn.com/jonathanme_testsvnrepo/jenkinsJobDSLDemo/branches/$release/" + interfaceName.replaceAll("$release-","")){
	  credentials('f469d47a-080c-4edf-b608-f4a15f085233')
        }
      }
    }
    steps{
      maven{
	goals('clean')
	goals('install')
	mavenInstallation('maven3.6')
      }
    }
  }
}
listView("$release-foo-interfaces"){
  description("All jobs for $release-foo-interfaces")
  jobs {
    interfaces.each{ name("$release-$it") }
  }
  columns {
    status()
    weather()
    name()
    lastSuccess()
    lastFailure()
    lastDuration()
    buildButton()
  }
}

