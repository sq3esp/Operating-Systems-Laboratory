package PageManagment;

public class Page {
	private int number;
	private int bitUsed;

	public Page(int number) {
		this.number = number;
	}

	public Page(int number, int bitUsed) {
		this.number = number;

		this.bitUsed = bitUsed;
	}

	public Page clone() {
		return new Page(number, bitUsed);
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getBitUsed() {
		return bitUsed;
	}

	public void setBitUsed(int bitUsed) {
		this.bitUsed = bitUsed;
	}

	@Override
	public String toString() {
		return "Page{" +
				"number=" + number +
				'}';
	}
}
