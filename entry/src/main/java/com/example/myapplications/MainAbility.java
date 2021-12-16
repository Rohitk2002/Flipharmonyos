package com.example.myapplications;

import com.example.myapplications.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorProperty;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Component.LayoutRefreshedListener;
import ohos.agp.components.Component.ClickedListener;
import ohos.agp.window.service.WindowManager;
import ohos.bundle.AbilityInfo;
import ohos.aafwk.content.IntentParams;
import ohos.aafwk.content.Intent;
import ohos.agp.components.DirectionalLayout;

public class MainAbility extends Ability {

    private DirectionalLayout belowLayout;

    //private CardView aboveLayout, btn;

    private Button aboveLayout, btn;

    private int height;

    private boolean isFlipped = false;

    //private OvershootInterpolator interpolator = new OvershootInterpolator();

    private int duration = 400;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        IntentParams savedInstanceState = intent.getParams();
//        getAbility().getWindow().addWindowFlags(WindowManager.LayoutConfig.MARK_FULL_SCREEN);
        setUIContent(ResourceTable.Layout_ability_main);
        // initialize
        belowLayout = (DirectionalLayout) findComponentById(ResourceTable.Id_belowLayout);
        aboveLayout = (Button) findComponentById(ResourceTable.Id_aboveLayout);
        btn = (Button) findComponentById(ResourceTable.Id_btn);
        aboveLayout.setLayoutRefreshedListener(new Component.LayoutRefreshedListener() {

            @Override
            public void onRefreshed(Component component) {
                // fetch height
                height = (aboveLayout.getHeight()) / 2;
            }
        });
        // button click listener
        btn.setClickedListener(new Component.ClickedListener() {

            @Override
            public void onClick(Component v) {
                //btn.animate().setInterpolator(interpolator).translationY(50).start();
                AnimatorProperty btnAnimatorProperty = btn.createAnimatorProperty();
                btnAnimatorProperty.setCurveType(Animator.CurveType.OVERSHOOT).moveByY(50).start();
                //aboveLayout.animate().setDuration(duration).setInterpolator(interpolator).translationY(height).start();
                aboveLayout.createAnimatorProperty().setCurveType(Animator.CurveType.OVERSHOOT).moveByY(height).start();
                AnimatorProperty belowLayoutAnimatorProperty = belowLayout.createAnimatorProperty();
                belowLayoutAnimatorProperty.setStateChangedListener(new Animator.StateChangedListener() {
                    @Override
                    public void onStart(Animator animator) {

                    }

                    @Override
                    public void onStop(Animator animator) {

                    }

                    @Override
                    public void onCancel(Animator animator) {

                    }

                    @Override
                    public void onEnd(Animator animator) {
                        if (!isFlipped) {
                            aboveLayout.setTranslationX(-50);
                            belowLayout.setTranslationX(0);
                            //btn.animate().setInterpolator(interpolator).rotation(45).start();
                            btnAnimatorProperty.setCurveType(Animator.CurveType.OVERSHOOT).rotate(45).start();
                        } else {
                            aboveLayout.setTranslationX(0);
                            belowLayout.setTranslationX(-50);
                            //btn.animate().setInterpolator(interpolator).rotation(0).start();
                            btnAnimatorProperty.setCurveType(Animator.CurveType.OVERSHOOT).rotate(0).start();
                        }
                        //belowLayout.animate().setDuration(duration).setInterpolator(interpolator).translationY(0).start();
                        belowLayoutAnimatorProperty.setDuration(duration).setCurveType(Animator.CurveType.OVERSHOOT).moveByY(0).start();
                        //btn.animate().setInterpolator(interpolator).translationY(0).start();
                        btnAnimatorProperty.setCurveType(Animator.CurveType.OVERSHOOT).moveByY(0).start();
                        isFlipped = !isFlipped;
                    }

                    @Override
                    public void onPause(Animator animator) {

                    }

                    @Override
                    public void onResume(Animator animator) {

                    }
                });
                belowLayoutAnimatorProperty.setDuration(duration).setCurveType(Animator.CurveType.OVERSHOOT).moveByY(-1*height).end();
                /*belowLayout.animate().setDuration(duration).setInterpolator(interpolator).translationY(-1 * height).withEndAction(new Runnable() {

                    @Override
                    public void run() {
                        if (!isFlipped) {
                            aboveLayout.setTranslationZ(-50);
                            belowLayout.setTranslationZ(0);
                            btn.animate().setInterpolator(interpolator).rotation(45).start();
                        } else {
                            aboveLayout.setTranslationZ(0);
                            belowLayout.setTranslationZ(-50);
                            btn.animate().setInterpolator(interpolator).rotation(0).start();
                        }
                        aboveLayout.animate().setDuration(duration).setInterpolator(interpolator).translationY(0).start();
                        belowLayout.animate().setDuration(duration).setInterpolator(interpolator).translationY(0).start();
                        btn.animate().setInterpolator(interpolator).translationY(0).start();
                        isFlipped = !isFlipped;
                    }
                }).start();*/
            }
        });
    }
}
