require 'java'

$CLASSPATH << 'target/classes'

java_import 'example.Foo'

class Bar < Foo
  def bar
  end
end

Foo.foo(Bar.new)
