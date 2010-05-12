package example;

public abstract class Foo {
	public static void foo(Foo b) {
		b.bar(new Object());
	}

	public abstract void bar(Object object);
}
