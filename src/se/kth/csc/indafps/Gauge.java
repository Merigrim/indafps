package se.kth.csc.indafps;

/**
 * Gauge is an object that limits a value inside an interval between 0 and a
 * given maximum value.
 * 
 * @author Marcus Åbrandt Östergren
 * @author Oscar Friberg
 * @version 2013-04-25
 */
public class Gauge {
    private int value;
    private int max;

    /**
     * Constructs a new gauge where max is the upper limit of the gauge.
	 * The gauge will by default be full.
     */
    Gauge(int max) {
		this.max = max;
		this.value = max;
    }

	/**
	 * Limits the value to the interval of [0, max] and returns the result.
	 */
	private int limit(int value) {
		value = Math.max(value, 0);
		value = Math.min(value, max);
		return value;
	}

	/**
	 * Sets the value of this Gauge. The new value of this Gauge is limited to
	 * the interval of [0, max].
	 *
	 * @param value The new value of this Gauge.
	 */
	public void setValue(int value) {
		this.value = limit(value);
	}

	/**
	 * Sets the upper limit of this Gauge. If the new upper limit is lower than
	 * the actual value of this Gauge, the value of this Gauge will be set to
	 * the new upper limit. The new upper limit is negative, the upper limit
	 * will be set to 0.
	 *
	 * @param max The new upper limit of this Gauge
	 */
	public void setMaximum(int max) {
		this.max = Math.max(0, max);
		value = limit(value);
	}

	/**
	 * @return The current value of this Gauge.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return The upper limit of this Gauge.
	 */
	public int getMaximum() {
		return max;
	}

    /**
     * Increases the value of this Gauge with diff. The new value of this Gauge
     * is limited to the interval of [0, max]. The value will decrease if diff
     * is negative.
     * 
     * @param diff The amount the value of this Gauge will increase.
     * @return The new value of this Gauge.
     */
    public int add(int diff) {
		value = limit(value + diff);
		return value;
    }

    /**
     * @return True if value is equals to max, otherwise false.
     */
    public boolean isFull() {
        return value == max;
    }

    /**
     * @return True if value is equals to 0, otherwise false.
     */
    public boolean isEmpty() {
        return value == 0;
    }

	/**
	 * Sets the value of this Gauge to max.
	 */
	public void refill() {
		value = max;
	}

	/**
	 * Sets the value of this Gauge to 0.
	 */
	public void empty() {
		value = 0;
	}
}
