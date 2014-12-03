package kr.hs.emirim.space.updown;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity implements OnClickListener {

	ImageView success;// 메인으로 가는 버튼
	ImageView btnMain;// 메인으로 가는 버튼
	ImageView btnDo;// Retry or Next
	TextView tvCount;// 시도 횟수 표시
	TextView tvAnswer;// 정답표시
	TextView tvLevel;// 레벨표시
	int count;// 시도횟수
	int answer;// 정답
	int level;// 현재레벨
	boolean isClear;// 클리어여부

	// 두번눌렀을때 뜨는 것
	private BackPressMainHandler backPressMainHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		backPressMainHandler = new BackPressMainHandler(this);

		Typeface type = Typeface.createFromAsset(getAssets(),
				"fonts/font_crayon.ttf");

		btnMain = (ImageView) findViewById(R.id.btn_main);
		btnMain.setOnClickListener(this);
		btnDo = (ImageView) findViewById(R.id.btn_do);
		btnDo.setOnClickListener(this);
		success = (ImageView) findViewById(R.id.image_ssuccess);
		tvCount = (TextView) findViewById(R.id.tv_chance);
		tvAnswer = (TextView) findViewById(R.id.tv_answer);
		tvLevel = (TextView) findViewById(R.id.tv_level);

		tvCount.setTypeface(type);
		tvAnswer.setTypeface(type);
		tvLevel.setTypeface(type);

		count = getIntent().getExtras().getInt("count");
		tvCount.setText("시도횟수 : " + count);
		answer = getIntent().getExtras().getInt("answer");
		tvAnswer.setText("정답 : " + answer);
		level = getIntent().getExtras().getInt("level");
		isClear = getIntent().getExtras().getBoolean("isClear");

		if (isClear) {// 맞췄을때
			tvLevel.setText("Level " + level + " -> Level " + (level + 1));
		} else {// 못맞췄을 때
			success.setImageResource(R.drawable.result_fail);
			btnDo.setImageResource(R.drawable.result_retry);
			tvLevel.setText("Level " + level);
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		if (v.getId() == R.id.btn_main) {// 메인으로 버튼
			intent = new Intent(this, MainActivity.class);
		} else if (v.getId() == R.id.btn_do && isClear) {// 다음레벨 버튼
			intent = new Intent(this, GameActivity.class);
			intent.putExtra("activityName", "next");
			intent.putExtra("level", (level > 10) ? level : ++level);
		} else if (v.getId() == R.id.btn_do && !(isClear)) {// 처음으로 버튼
			intent = new Intent(this, GameActivity.class);
			intent.putExtra("activityName", "restart");
		}
		finish();
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		if (backPressMainHandler.onBackPressed()) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}

}
