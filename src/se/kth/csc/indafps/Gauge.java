package se.kth.csc.indafps;

/**
 * Gauge is an object that limits a value inside an intervall
 * between 0 and a given maximum value.
 *
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */

public class Gauge {
	private int value;
	private int max;

	/**
	 * Constructs a new gauge where max is the upper limit of
	 * the gauge.
	 */
	Gauge(int max) {
	}

	/**
	 * Increases the value of this Gauge with diff. The new
	 * value of this Gauge is limited to the interval of
	 * [0, max]. The value will decrease if diff is negative.
	 * @param diff The amount the value of this Gauge will
	 * increase.
	 * @return The new value of this Gauge.
	 */
	public int add(int diff) {
		return 0;
	}

	/**
	 * @return True if value is equals to max, otherwise false.
	 */
	public boolean isFull() {
		return false;
	}

	/**
	 * @return True if value is equals to 0, otherwise false.
	 */
	public boolean isEmpty() {
		return false;
	}
}
