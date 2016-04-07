package turner.deadlock;

public class DiningPhilosophers {

	public static void main(String[] args) {

		Fork f1 = new Fork(1);
		Fork f2 = new Fork(2);
		Fork f3 = new Fork(3);
		Fork f4 = new Fork(4);
		Fork f5 = new Fork(5);

		Philosopher a = new Philosopher("A", f1, f2);
		Philosopher b = new Philosopher("B", f2, f3);
		Philosopher c = new Philosopher("C", f3, f4);
		Philosopher d = new Philosopher("D", f4, f5);
		Philosopher e = new Philosopher("E", f5, f1);

		a.start();
		b.start();
		c.start();
		d.start();
		e.start();

	}

}
