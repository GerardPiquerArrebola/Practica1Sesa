package p01_CompareAndSet;

import java.util.concurrent.atomic.AtomicBoolean;

import p00_Common.AbstractBoard;

public class CompareAndSetBoard extends AbstractBoard {
	
	private AtomicBoolean boardBusy = new AtomicBoolean(false);
	
	/* Add all the boolean attributes you need 
	 * (no AtomicBoolean)
	 */
	private boolean canPaper = true;
	private boolean canRock = true;
	private boolean canScissors = true;
	private boolean canJudge = false;
	
	private volatile int count = 0;
	
	
	public void letMeAccessForPaper(int id) {
		/* COMPLETE */
		while (!boardBusy.compareAndSet(canPaper, false))
			Thread.yield();
		count++;
	}
	
	public void letMeAccessForRock(int id) {
		/* COMPLETE */
		while (!boardBusy.compareAndSet(canRock, false))
			Thread.yield();
		count++;
	}
	
	public void letMeAccessForScissors(int id) {
		/* COMPLETE */
		while (!boardBusy.compareAndSet(canScissors, false))
			Thread.yield();
		count++;
	}
	
	public void letMeAccessForJudge (int id) {
		/* COMPLETE */
		while (!boardBusy.compareAndSet(canJudge, false) && count < 2)
			Thread.yield();
	}
	
	public void accessDone () {
		/* COMPLETE */
		if (count == 2) {
			canJudge = true;
			canPaper = false;
			canRock = false;
			canScissors = false;
		}
	}
}
