package net.devyy.yi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import net.devyy.yi.R;

/**
 * 自定义 View，“联系人” 页面 右侧导航条
 */
public class ContactsSlideBar extends View {

    private Paint mPaint;
    //    private float mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics());
    private float mTextSize = 0;
    private static final String[] LETTERS = {
            "↑", "☆", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z", "#"
    };
//    private int mCellWidth;
//    private float mCellHeight;
    private float mTextBaseline = 0;
    private int mTouchIndex = -1;   // 用于记录当前触摸的索引值

    private OnSlideBarChangedListener mListener;

    public ContactsSlideBar(Context context) {
        this(context, null);
    }

    public ContactsSlideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContactsSlideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//
//        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setColor(getResources().getColor(R.color.module_slideBar_text_color));
//        mPaint.setTextSize(mTextSize);
        initPaint();
    }
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.module_slide_bar_text_color));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(sp2px(getContext(), 14));
    }

    private int sp2px(Context var0, float var1) {
        float var2 = var0.getResources().getDisplayMetrics().scaledDensity;
        return (int)(var1 * var2 + 0.5F);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//
//        // 获取单元格的宽度和高度
//        mCellWidth = getMeasuredWidth();
//        mCellHeight = getMeasuredHeight() * 1.0f / LETTERS.length;

        mTextSize = h * 1.0f / LETTERS.length;//计算分配给字符的高度
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float mTextHeight = fontMetrics.descent - fontMetrics.ascent;//获取绘制字符的实际高度
        mTextBaseline = mTextSize / 2 + mTextHeight/2 - fontMetrics.descent;//计算字符居中时的baseline

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        setBackgroundColor(Color.TRANSPARENT);
//
//        for (int i = 0; i < LETTERS.length; i++) {
//            String text = LETTERS[i];
//            // 计算坐标
//            // x坐标为单元格宽度的一半 减去 文字宽度的一半
//            int x = (int) (mCellWidth / 2.0f - mPaint.measureText(text) / 2.0f);
//
//            Rect bounds = new Rect();
//            mPaint.getTextBounds(text, 0, text.length(), bounds);
//            // 文本的高度
//            int textHeight = bounds.height();
//
//            // y坐标为单元格高度的一半 + 文字高度的一半 + 上面有多少个单元格的高度(index * mCellHeight)
//            int y = (int) (mCellHeight / 2.0f + textHeight / 2.0f + i * mCellHeight);
//
//            // mPaint.setColor(mTouchIndex == i ? Color.GRAY : Color.WHITE);//被选择到的字母变成灰色
//            // 绘制文本A-Z，此处的x，y坐标是字母左上方的坐标
//            canvas.drawText(text, x, y, mPaint);
//        }

        float x = getWidth() * 1.0f / 2;
        float baseline = mTextBaseline;
        for(int i = 0; i < LETTERS.length; i++) {
            canvas.drawText(LETTERS[i], x, baseline, mPaint);
            baseline += mTextSize;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(getResources().getColor(R.color.module_slide_bar_pressed_color));
                notifySectionChange(event);

                break;
            case MotionEvent.ACTION_MOVE:
                notifySectionChange(event);
//                setBackgroundColor(getResources().getColor(R.color.module_slideBar_pressed_color));
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.TRANSPARENT);
                if (mListener != null) {
                    mListener.onSlidingFinish();
                }

                break;
        }
//        invalidate();//重新调用onDraw方法实现选中的字母更改颜色
        return true;
    }

    private void notifySectionChange(MotionEvent event) {
        int index = getTouchIndex(event);
        if (mListener != null && mTouchIndex != index) {
            mTouchIndex = index;
            mListener.onSectionChange(index, LETTERS[index]);
        }
    }

    private int getTouchIndex(MotionEvent event) {
        int index = (int) (event.getY() / mTextSize);
        if (index < 0) {
            index = 0;
        } else if (index > LETTERS.length - 1) {
            index = LETTERS.length - 1;
        }
        return index;
    }

    // 内部监听器接口
    public interface OnSlideBarChangedListener {
        void onSectionChange(int index, String section);

        void onSlidingFinish( );
    }

    public void setSlideBarChangedListener(OnSlideBarChangedListener mListener) {
        this.mListener = mListener;
    }
}
