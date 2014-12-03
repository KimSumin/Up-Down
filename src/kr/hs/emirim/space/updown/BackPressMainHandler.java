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
		toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르면 메인으로 돌아갑니다.",
				Toast.LENGTH_SHORT);
		toast.show();
	}
}