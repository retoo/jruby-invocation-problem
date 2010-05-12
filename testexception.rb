require 'java'

$CLASSPATH << 'target/classes'

java_import 'example.AbstractJobFactory'
java_import 'example.BackgroundJob'
java_import 'example.Job'

class RubyJob < Job
  def doJob
    puts "doing a job"
  end
end
class JobFactory < AbstractJobFactory
  def createJob
    return RubyJob.new
  end
end

e = BackgroundJob.new
e.start
factory = JobFactory.new
e.register(factory)
e.shutdown
