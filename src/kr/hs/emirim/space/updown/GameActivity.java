package kr.hs.emirim.space.updown;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements OnClickListener {

	int randomNum;// 랜덤숫자
	int count;// 시도횟수
	int maxCount = 10;// 최대시도횟수
	int level = 1;
	int range = 10;
	ImageView btnNum[] = new ImageView[10];// 숫자버튼들
	ImageView btnBack, btnEnter, UpDown;// 한글자 지우기, 확인, 업다운이미지
	TextView tvInput, tvInputted;// 현재입력, 이전입력
	TextView tvLevel, tvCount;// 현재레벨, 시도횟수
	TextView tvInfo;// 현재레벨, 시도횟수
	String activityName;
	// 두번눌렀을때 뜨는 것
	private BackPressMainHandler backPressMainHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		backPressMainHandler = new BackPressMainHandler(this);

		Typeface type = Typeface.createFromAsset(getAssets(),
				"fonts/font_crayon.ttf");

		tvInput = (TextView) findViewById(R.id.tv_input);
		tvInputted = (TextView) findViewById(R.id.tv_pastinput);
		btnBack = (ImageView) findViewById(R.id.btn_back);
		btnEnter = (ImageView) findViewById(R.id.btn_enter);
		UpDown = (ImageView) findViewById(R.id.updown);
		tvLevel = (TextView) findViewById(R.id.tv_level);
		tvCount = (TextView) findViewById(R.id.tv_chance);
		tvInfo = (TextView) findViewById(R.id.tv_info);

		tvInput.setTypeface(type);
		tvInputted.setTypeface(type);
		tvInfo.setTypeface(type);
		tvLevel.setTypeface(type);
		tvCount.setTypeface(type);

		String packName = this.getPackageName(); // 패키지명
		for (int i = 0; i < btnNum.length; i++) {
			String viewName = "btn_num" + i;
			int resID = getResources().getIdentifier(viewName, "id", packName);
			btnNum[i] = (ImageView) findViewById(resID);
			btnNum[i].setOnClickListener(this);
		}// end for i
		btnBack.setOnClickListener(this);
		btnEnter.setOnClickListener(this);

		// 램덤숫자 생성
		activityName = getIntent().getExtras().getString("activityName");
		if (activityName.equals("next")) {
			level = getIntent().getExtras().getInt("level");
			for (int i = 1; i < level; i++) {
				range = (level == 11) ? (9999) : (range * 2);
			}
		}
		randomNum = (int) (Math.random() * range) + 1;
		maxCount += 1 * (level / 3);
		
		
		//Toast.makeText(this, randomNum+"", Toast.LENGTH_SHORT).show();
		tvLevel.setText("Level : " + level);
		tvCount.setText(count + "/" + maxCount);
		tvInfo.setText("1~" + range + "사이의 정답을 찾으세요!");

	}// end onCreate

	@Override
	public void onClick(View v) {
		String str = tvInput.getText().toString();
		int nLength = str.length();
		
		if (nLength != 0) {
			if (v.getId() == R.id.btn_back) {
				str = str.substring(0, nLength - 1);
				tvInput.setText(str);
			}
			if (v.getId() == R.id.btn_enter) {
				clickEnter(str);
				return;
			}
		}
		clicknum(v, str);

	}// onClick

	public void clickEnter(String str) {
		Intent intent = new Intent(this, ResultActivity.class);
		int value = Integer.valueOf(str);
		if (TextUtils.isEmpty(str) || (value > range) || (value == 0)) {
			Toast.makeText(this, "1~" + range + " 사이의 값을 입력해주세요!",
					Toast.LENGTH_SHORT).show();
			return;
		}
		count++;
		int userNum = Integer.parseInt(str);
		if (randomNum == userNum) {
			intent.putExtra("count", count);
			intent.putExtra("answer", randomNum);
			intent.putExtra("level", level);
			intent.putExtra("isClear", true);
			finish();
			startActivity(intent);
			return;
		}
		if (count >= maxCount) {
			intent.putExtra("isClear", false);
			intent.putExtra("answer", randomNum);
			intent.putExtra("level", level);
			intent.putExtra("count", count);
			finish();
			startActivity(intent);
		}
		if (randomNum > userNum) {
			UpDown.setImageResource(R.drawable.game_up);
		} else if (randomNum < userNum) {
			UpDown.setImageResource(R.drawable.game_down);
		}
		tvInputted.setText(str);
		tvInput.setText("");

		tvCount.setText(count + "/" + maxCount);
	}// void clickEnter

	public void clicknum(View v, String str) {
		// FIXME 각 range에 따른 입력가능범위제한표시
		// str의 가장 첫 자리가 range의 첫 자리와 동일해야 하며,

		if (str.length() > 3) {
			Toast.makeText(this, "최대 9999까지 입력할수 있습니다.", Toast.LENGTH_LONG)
					.show();
			return;
		}// end if

		/*
		 * //TODO 둘중에 어느 방법이 더 빠르고 휴대폰의 배터리를 적게 사용하는지 알아보고 적용하기! String packName
		 * = this.getPackageName(); // 패키지명 for(int i=0; i<btnNum.length; i++){
		 * String viewName = "btn_num"+i; int resID =
		 * getResources().getIdentifier(viewName, "id", packName); if(v.getId()
		 * == resID){ str+=i; break; }//end if }//end for i
		 */

		switch (v.getId()) {
		case R.id.btn_num0:
			str += 0;
			break;
		case R.id.btn_num1:
			str += 1;
			break;
		case R.id.btn_num2:
			str += 2;
			break;
		case R.id.btn_num3:
			str += 3;
			break;
		case R.id.btn_num4:
			str += 4;
			break;
		case R.id.btn_num5:
			str += 5;
			break;
		case R.id.btn_num6:
			str += 6;
			break;
		case R.id.btn_num7:
			str += 7;
			break;
		case R.id.btn_num8:
			str += 8;
			break;
		case R.id.btn_num9:
			str += 9;
			break;
		default:
			break;
		}// end switch

		tvInput.setText(str);
	}// clickBtn_num

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