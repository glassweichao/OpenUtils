package com.chaow.openutils.basic;

import android.content.res.Resources;
import android.graphics.BlurMaskFilter;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import com.chaow.openutils.builder.Builder;


/**
 * @author : Char
 * @date : 2019/9/4
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    :
 */
public final class SpanUtils implements Builder<SpannableStringBuilder> {

    private SpannableStringBuilder mSpannableStringBuilder;
    private TextView mTextView;
    private int flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
    private int start;
    private int end;

    private SpanUtils() {
    }

    public SpanUtils(String text) {
        this(text, null);
    }

    public SpanUtils(String text, TextView textView) {
        if (textView != null) {
            mTextView = textView;
        }
        mSpannableStringBuilder = new SpannableStringBuilder(text);
        start = 0;
        end = text.length();
    }

    @Override
    public SpannableStringBuilder build() {
        if (mSpannableStringBuilder == null) {
            throw new RuntimeException("call Build construction first");
        }
        return mSpannableStringBuilder;
    }

    public void apply() {
        if (mSpannableStringBuilder == null) {
            throw new RuntimeException("call Build construction first");
        }
        if (mTextView == null) {
            throw new RuntimeException("SpanUtils(String text, TextView textView) must have been called");
        }
        mTextView.setText(mSpannableStringBuilder);
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

    public SpanUtils setStartEnd(@IntRange(from = 0) int start, @IntRange(from = 0) int end) {
        if (start < end) {
            this.start = start;
            this.end = end;
        } else {
            throw new RuntimeException("start must < end");
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

    /**
     * 设置列表标记
     *
     * @param gapWidth 标记宽
     * @param start    开始位
     * @param end      结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setBullet(@IntRange(from = 0) final int gapWidth, int start, int end) {
        mSpannableStringBuilder.setSpan(new BulletSpan(gapWidth), start, end, flag);
        return this;
    }

    public SpanUtils setBullet(@IntRange(from = 0) final int gapWidth) {
        return setBullet(gapWidth, start, end);
    }

    /**
     * 设置字体大小
     *
     * @param size  字体大小，dip
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setFontSize(@IntRange(from = 0) final int size, int start, int end) {
        mSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(size), start, end, flag);
        return this;
    }

    public SpanUtils setFontSize(@IntRange(from = 0) final int size) {
        return setFontSize(size, start, end);
    }

    /**
     * 设置字体比例
     *
     * @param proportion 比例
     * @param start      开始位
     * @param end        结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setFontProportion(@FloatRange(from = 0) float proportion, int start, int end) {
        mSpannableStringBuilder.setSpan(new RelativeSizeSpan(proportion), start, end, flag);
        return this;
    }

    public SpanUtils setFontProportion(@FloatRange(from = 0) float proportio) {
        return setFontProportion(proportio);
    }

    /**
     * 设置字体横向比例
     *
     * @param x     横向比例
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setFontXProportion(@FloatRange(from = 0) float x, int start, int end) {
        mSpannableStringBuilder.setSpan(new ScaleXSpan(x), start, end, flag);
        return this;
    }

    public SpanUtils setFontXProportion(@FloatRange(from = 0) float x) {
        return setFontXProportion(x, start, end);
    }

    /**
     * 设置删除线
     *
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setStrikethrough(int start, int end) {
        mSpannableStringBuilder.setSpan(new StrikethroughSpan(), start, end, flag);
        return this;
    }

    public SpanUtils setStrikethrough() {
        return setStrikethrough(start, end);
    }

    /**
     * 设置下划线
     *
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setUnderline(int start, int end) {
        mSpannableStringBuilder.setSpan(new UnderlineSpan(), start, end, flag);
        return this;
    }

    public SpanUtils setUnderline() {
        return setUnderline(start, end);
    }

    /**
     * 设置上标
     *
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setSuperscript(int start, int end) {
        mSpannableStringBuilder.setSpan(new SuperscriptSpan(), start, end, flag);
        return this;
    }

    public SpanUtils setSuperscript() {
        return setSuperscript(start, end);
    }

    /**
     * 设置下标
     *
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setSubscript(int start, int end) {
        mSpannableStringBuilder.setSpan(new SubscriptSpan(), start, end, flag);
        return this;
    }

    public SpanUtils setSubscript() {
        return setSubscript(start, end);
    }

    /**
     * 设置粗体
     *
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setBold(int start, int end) {
        mSpannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), start, end, flag);
        return this;
    }

    public SpanUtils setBold() {
        return setBold(start, end);
    }

    /**
     * 设置斜体
     *
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setItalic(int start, int end) {
        mSpannableStringBuilder.setSpan(new StyleSpan(Typeface.ITALIC), start, end, flag);
        return this;
    }

    public SpanUtils setItalic() {
        return setItalic(start, end);
    }

    /**
     * 设置斜粗体
     *
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setBoldItalic(int start, int end) {
        mSpannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), start, end, flag);
        return this;
    }

    public SpanUtils setBoldItalic() {
        return setBoldItalic(start, end);
    }

    /**
     * 设置字体未默认普通样式
     *
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setFontTypeNormal(int start, int end) {
        mSpannableStringBuilder.setSpan(new StyleSpan(Typeface.NORMAL), start, end, flag);
        return this;
    }

    public SpanUtils setFontTypeNormal() {
        return setFontTypeNormal(start, end);
    }

    /**
     * 设置字体
     *
     * @param fontFamily 字体
     * @param start      开始位
     * @param end        结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setFontFamily(@NonNull final String fontFamily, int start, int end) {
        mSpannableStringBuilder.setSpan(new TypefaceSpan(fontFamily), start, end, flag);
        return this;
    }

    public SpanUtils setFontFamily(@NonNull final String fontFamily) {
        return setFontFamily(fontFamily);
    }

    /**
     * 设置点击事件
     *
     * @param clickSpan 点击事件
     * @param start     开始位
     * @param end       结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setClickSpan(@NonNull final ClickableSpan clickSpan, int start, int end) {
        if (mTextView != null && mTextView.getMovementMethod() == null) {
            mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        mSpannableStringBuilder.setSpan(clickSpan, start, end, flag);
        return this;
    }

    public SpanUtils setClickSpan(@NonNull final ClickableSpan clickSpan) {
        return setClickSpan(clickSpan);
    }

    /**
     * 设置URL
     *
     * @param url   the url
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setUrl(@NonNull final String url, int start, int end) {
        if (mTextView != null && mTextView.getMovementMethod() == null) {
            mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        mSpannableStringBuilder.setSpan(new URLSpan(url), start, end, flag);
        return this;
    }

    public SpanUtils setUrl(@NonNull final String url) {
        return setUrl(url, start, end);
    }

    /**
     * 设置模糊效果
     *
     * @param radius The radius to extend the blur from the original mask. Must be > 0.
     * @param style  The Blur to use
     * @param start  开始位
     * @param end    结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setBlur(@FloatRange(from = 0, fromInclusive = false) final float radius, final BlurMaskFilter.Blur style, int start, int end) {
        mSpannableStringBuilder.setSpan(new MaskFilterSpan(new BlurMaskFilter(radius, style)), start, end, flag);
        return this;
    }

    public SpanUtils setBlur(@FloatRange(from = 0, fromInclusive = false) final float radius, final BlurMaskFilter.Blur style) {
        return setBlur(radius, style, start, end);
    }

    /**
     * 设置着色器
     *
     * @param shader 着色器
     * @param start  开始位
     * @param end    结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setShaderSpan(Shader shader, int start, int end) {
        mSpannableStringBuilder.setSpan(new ShaderSpan(shader), start, end, flag);
        return this;
    }

    public SpanUtils setShaderSpan(Shader shader) {
        return setShaderSpan(shader);
    }

    /** 着色器Span */
    static class ShaderSpan extends CharacterStyle implements UpdateAppearance {
        private Shader mShader;

        private ShaderSpan(final Shader shader) {
            this.mShader = shader;
        }

        @Override
        public void updateDrawState(final TextPaint tp) {
            tp.setShader(mShader);
        }
    }

    /**
     * 设置阴影
     *
     * @param radius      阴影半径
     * @param dx          x偏移量
     * @param dy          y偏移量
     * @param shadowColor 阴影颜色
     * @param start       开始位
     * @param end         结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setShadow(@FloatRange(from = 0, fromInclusive = false) final float radius, final float dx, final float dy, final int shadowColor, int start, int end) {
        mSpannableStringBuilder.setSpan(new ShadowSpan(radius, dx, dy, shadowColor), start, end, flag);
        return this;
    }

    public SpanUtils setShadow(@FloatRange(from = 0, fromInclusive = false) final float radius, final float dx, final float dy, final int shadowColor) {
        return setShadow(radius, dx, dy, shadowColor);
    }

    /** 阴影Span */
    static class ShadowSpan extends CharacterStyle implements UpdateAppearance {
        private float radius;
        private float dx, dy;
        private int shadowColor;

        private ShadowSpan(final float radius,
                           final float dx,
                           final float dy,
                           final int shadowColor) {
            this.radius = radius;
            this.dx = dx;
            this.dy = dy;
            this.shadowColor = shadowColor;
        }

        @Override
        public void updateDrawState(final TextPaint tp) {
            tp.setShadowLayer(radius, dx, dy, shadowColor);
        }
    }

    /**
     * 设置自定义spans
     *
     * @param start 开始位
     * @param end   结束位
     * @param spans span集合
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setSpan(int start, int end, @NonNull final Object... spans) {
        mSpannableStringBuilder.setSpan(spans, start, end, flag);
        return this;
    }

    public SpanUtils setSpan(@NonNull final Object... spans) {
        return setSpan(start, end, spans);
    }

    /**
     * 插入字符串
     *
     * @param where 插入位置
     * @param text  字符串
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils inert(int where, CharSequence text) {
        mSpannableStringBuilder.insert(where, text);
        return this;
    }

    /**
     * 插入字符串
     *
     * @param where 插入位置
     * @param text  字符串
     * @param start 开始位
     * @param end   结束位
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils inert(int where, CharSequence text, int start, int end) {
        mSpannableStringBuilder.insert(where, text, start, end);
        return this;
    }


}
