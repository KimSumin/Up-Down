package kr.hs.emirim.space.updown;

import android.app.Activity;
import android.widget.Toast;

public class BackPressMainHandler {

	private long backKeyPressedTime = 0;
	private Toast toast;

	private Activity activity;

	public BackPressMainHandler(Activity context) {
		this.activity = context;
	}

	public boolean onBackPressed() {
		if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
			backKeyPressedTime = System.currentTimeMillis();
			showGuide();
			return false;
		}
		if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
			toast.cancel();
			return true;
		}
		return false;
	}

	public void showGuide() {
		toast = Toast.makeText(activity, "\'�ڷ�\'��ư�� �ѹ� �� ������ �������� ���ư��ϴ�.",
				Toast.LENGTH_SHORT);
		toast.show();
	}
}