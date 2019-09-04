package com.chaow.openutils.basic;

import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.QuoteSpan;

import androidx.annotation.ColorInt;

import com.chaow.openutils.builder.Builder;


/**
 * @author : Char
 * @date : 2019/9/4
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class SpanUtils implements Builder<SpannableStringBuilder> {

    private SpannableStringBuilder mSpannableStringBuilder;
    private int flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
    private int start;
    private int end;

    private SpanUtils() {
    }

    public SpanUtils(String text) {
        mSpannableStringBuilder = new SpannableStringBuilder(text);
        start = 0;
        end = text.length();
    }

    @Override
    public SpannableStringBuilder build() {
        if (mSpannableStringBuilder == null) {
            throw new IllegalArgumentException("call Build construction first");
        }
        return mSpannableStringBuilder;
    }

    /**
     * @param flag The flag.
     *             <ul>
     *             <li>{@link Spanned#SPAN_INCLUSIVE_EXCLUSIVE}</li>
     *             <li>{@link Spanned#SPAN_INCLUSIVE_INCLUSIVE}</li>
     *             <li>{@link Spanned#SPAN_EXCLUSIVE_EXCLUSIVE}</li>
     *             <li>{@link Spanned#SPAN_EXCLUSIVE_INCLUSIVE}</li>
     *             </ul>
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setFlag(int flag) {
        this.flag = flag;
        return this;
    }

    public SpanUtils setStartEnd(int start, int end) {
        if (start < end) {
            this.start = start;
            this.end = end;
        }
        return this;
    }

    /**
     * 设置前景色
     *
     * @param color To get the color integer associated with a particular color resource ID, use
     *              {@link android.content.res.Resources#getColor(int, Resources.Theme)}
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setForegroundColor(@ColorInt int color, int start, int end) {
        mSpannableStringBuilder.setSpan(new ForegroundColorSpan(color), start, end, flag);
        return this;
    }

    public SpanUtils setForegroundColor(@ColorInt int color) {
        return setForegroundColor(color, start, end);
    }

    /**
     * 设置背景色
     *
     * @param color To get the color integer associated with a particular color resource ID, use
     *              {@link android.content.res.Resources#getColor(int, Resources.Theme)}
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setBackgroundColor(@ColorInt int color, int start, int end) {
        mSpannableStringBuilder.setSpan(new BackgroundColorSpan(color), start, end, flag);
        return this;
    }

    public SpanUtils setBackgroundColor(@ColorInt int color) {
        return setBackgroundColor(color, start, end);
    }

    /**
     * 设置引用线颜色
     *
     * @param color To get the color integer associated with a particular color resource ID, use
     *              {@link android.content.res.Resources#getColor(int, Resources.Theme)}
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setQuoteColor(@ColorInt int color, int start, int end) {
        mSpannableStringBuilder.setSpan(new QuoteSpan(color), start, end, flag);
        return this;
    }

    public SpanUtils setQuoteColor(@ColorInt int color) {
        return setQuoteColor(color, start, end);
    }

    /**
     * 设置缩进
     *
     * @param first 首行缩进
     * @param rest  剩余行缩进
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setLeadingMargin(int first, int rest, int start, int end) {
        mSpannableStringBuilder.setSpan(new LeadingMarginSpan.Standard(first, rest), start, end, flag);
        return this;
    }

    public SpanUtils setLeadingMargin(int first, int rest) {
        return setLeadingMargin(first, rest, start, end);
    }

    public SpanUtils setBullet() {
        mSpannableStringBuilder.setSpan(new BulletSpan(), start, end, flag);
        return this;
    }

    public SpanUtils textSize(int size, int start, int end) {
        mSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(size, true), start, end, flag);
        return this;
    }

}
