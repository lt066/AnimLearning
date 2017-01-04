package example.com.animationlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        imageView= (ImageView) findViewById(R.id.image);
        findViewById(R.id.btn_alpha).setOnClickListener(new AlphaButtonListener());
        findViewById(R.id.btn_tran).setOnClickListener(new TranButtonListener());
        findViewById(R.id.btn_scale).setOnClickListener(new ScaleButtonListener());
        findViewById(R.id.btn_rotate).setOnClickListener(new RotateButtonListener());
        findViewById(R.id.btn_alpha2).setOnClickListener(this);
        findViewById(R.id.btn_tran2).setOnClickListener(this);
        findViewById(R.id.btn_scale2).setOnClickListener(this);
        findViewById(R.id.btn_rotate2).setOnClickListener(this);
    }

    /**
     * 调用xml的透明效果动画
     */
    private void xmlAlpha(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        imageView.startAnimation(animation);
    }

    /**
     * 调用xml的移动动画
     */
    private void xmlTran(){
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.tran_anim);
        imageView.startAnimation(animation);
        imageView.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
              Animation animation2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.tran_anim2);
                imageView.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 调用xml的缩放动画
     */
    private void xmlScale(){
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        imageView.startAnimation(animation);
    }

    /**
     * 调用xml的旋转动画
     */
    private void xmlRotate(){
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        imageView.startAnimation(animation);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_alpha2:
                xmlAlpha();
                break;
            case R.id.btn_tran2:
                xmlTran();
                break;
            case R.id.btn_scale2:
                xmlScale();
                break;
            case R.id.btn_rotate2:
                xmlRotate();
                break;

        }
    }

    /**
     * 按钮淡入淡出监听
     * setDuration(long durationMills)
     　设置动画持续时间（单位：毫秒）
     　setFillAfter(Boolean fillAfter)
     　如果fillAfter的值为true,则动画执行后，控件将停留在执行结束的状态
     　setFillBefore(Boolean fillBefore)
     　如果fillBefore的值为true，则动画执行后，控件将回到动画执行之前的状态
     　setStartOffSet(long startOffSet)
     　设置动画执行之前的等待时间
     　setRepeatCount(int repeatCount)
     　设置动画重复执行的次数
     */
    class AlphaButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //创建一个AnimationSet对象，参数为Boolean型，
            //true表示使用Animation的interpolator，false则是使用自己的
            AnimationSet animationSet = new AnimationSet(true);
            //创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
            AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
            alphaAnimation.setDuration(500);
            animationSet.addAnimation(alphaAnimation);
            imageView.startAnimation(animationSet);
        }
    }

    /**
     * 按钮移动监听
     */
    class TranButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1～2：x轴的开始位置
            //参数3～4：y轴的开始位置
            //参数5～6：x轴的结束位置
            //参数7～8：x轴的结束位置
            TranslateAnimation translateAnimation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF,0f
                    ,Animation.RELATIVE_TO_SELF,0.5f
                    ,Animation.RELATIVE_TO_SELF,0f
                    ,Animation.RELATIVE_TO_SELF,0.5f);
            translateAnimation.setDuration(1000);
            animationSet.addAnimation(translateAnimation);
            imageView.startAnimation(animationSet);
        }
    }

    /**
     * 按钮缩放监听
     */
    class ScaleButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            AnimationSet animationSet = new AnimationSet(true);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
            alphaAnimation.setDuration(1000);
            //参数1：x轴的初始值
            //参数2：x轴收缩后的值
            //参数3：y轴的初始值
            //参数4：y轴收缩后的值
            //参数5：确定x轴坐标的类型
            //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            //参数7：确定y轴坐标的类型
            //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    0f,1.0f
                    ,0f,1.0f
                    ,Animation.RELATIVE_TO_SELF,0.5f
                    ,Animation.RELATIVE_TO_SELF,0.5f);
            scaleAnimation.setDuration(1000);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(alphaAnimation);
            imageView.startAnimation(animationSet);
        }
    }

    /**
     * 按钮旋转监听
     */
    class RotateButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1：从哪个旋转角度开始
            //参数2：转到什么角度
            //后4个参数用于设置围绕着旋转的圆的圆心在哪里
            //参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
            //参数4：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            //参数5：确定y轴坐标的类型
            //参数6：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            RotateAnimation rotateAnimation = new RotateAnimation(
                    0,360
                    ,Animation.RELATIVE_TO_SELF,0.5f
                    ,Animation.RELATIVE_TO_SELF,0.5f);
            rotateAnimation.setDuration(1000);
            animationSet.addAnimation(rotateAnimation);
            imageView.startAnimation(animationSet);
        }
    }
}
