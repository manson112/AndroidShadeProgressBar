package me.manson112.custom.shadeprogressbar

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import kotlin.math.roundToInt

class ShadeProgressBar @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = R.attr.DefStyle
    ): View(context, attrs, defStyleAttr) {
        private var shade = RectF()
        // Item, Shade Color
        @ColorInt private var _itemBackgroundColor = Color.parseColor(DEFAULT_ITEM_COLOR)
        @ColorInt private var _shadeColor = Color.parseColor(DEFAULT_SHADE_COLOR)

        @Dimension private var _sideCornerRadius = context.d2p(DEFAULT_CORNER_RADIUS)
        @Dimension private var _itemSize = context.d2p(DEFAULT_IMAGE_SIZE)
        @Dimension private var _shadeSize = context.d2p(DEFAULT_IMAGE_SIZE)

        @DrawableRes private var _itemRes: Int = INVALID_RES

        private var _shadeRadius = DEFAULT_SHADE_DIRECTION

        private var _itemAnimDuration = DEFAULT_ANIM_DURATION
        private var _itemPercentage: Int = 0
        private lateinit var item: Item
        private lateinit var progressAnimator: ValueAnimator

        var shadeColor: Int
            @ColorInt get() = _shadeColor
            set(@ColorInt value) {
                _shadeColor = value
                paintShade.color = value
                invalidate()
            }

        var shadeSize: Float
            @Dimension get() = _shadeSize
            set(@Dimension value) {
                _shadeSize = value
                invalidate()
            }
        var shadeRadius: Int
            get() = _shadeRadius
            set(value) {
                _shadeRadius = value % 360
                setShadeLocation()
                invalidate()
            }
        var sideCornerRadius: Float
            @Dimension get() = _sideCornerRadius
            set(@Dimension value) {
                _sideCornerRadius = value
                invalidate()
            }
        var itemBackgroundColor: Int
            @ColorInt get() = _itemBackgroundColor
            set(@ColorInt value) {
                _itemBackgroundColor = value
                paintBackground.color = value
                invalidate()
            }
        var itemSize: Float
            @Dimension get() = _itemSize
            set(@Dimension value) {
                _itemSize = value
                invalidate()
            }
        var itemRes: Int
            @DrawableRes get() = _itemRes
            set(@DrawableRes value) {
                _itemRes = value
                item = ItemParser(context, value).parse()
                invalidate()
            }
        var itemAnimDuration: Long
            get() = _itemAnimDuration
            set(value) {
                _itemAnimDuration = value
            }
        var itemPercentage: Int
            get() = _itemPercentage
            set(value) {
                _itemPercentage = value
                invalidate()
            }
        private val paintBackground = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = itemBackgroundColor
        }

        private val paintShade = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = shadeColor
        }

        init {
            obtainStyledAttributes(attrs, defStyleAttr)
        }
        private fun obtainStyledAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
            val typedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ShadeProgressBar,
                defStyleAttr,
                0
            )
            try {
                itemRes = typedArray.getResourceId(
                    R.styleable.ShadeProgressBar_src,
                    itemRes
                )
                shadeColor = typedArray.getColor(
                    R.styleable.ShadeProgressBar_shadeColor,
                    shadeColor
                )
                shadeSize = typedArray.getDimension(
                    R.styleable.ShadeProgressBar_shadeSize,
                    shadeSize
                )
                shadeRadius = typedArray.getInt(
                    R.styleable.ShadeProgressBar_shadeRadius,
                    shadeRadius
                )
                itemBackgroundColor = typedArray.getColor(
                    R.styleable.ShadeProgressBar_backgroundColor,
                    itemBackgroundColor
                )
                sideCornerRadius = typedArray.getDimension(
                    R.styleable.ShadeProgressBar_sideCornerRadius,
                    sideCornerRadius
                )
                itemSize = typedArray.getDimension(
                    R.styleable.ShadeProgressBar_itemSize,
                    itemSize
                )
                itemAnimDuration = typedArray.getInt(
                    R.styleable.ShadeProgressBar_duration,
                    itemAnimDuration.toInt()
                ).toLong()

                itemPercentage = typedArray.getInt(
                    R.styleable.ShadeProgressBar_percentage,
                    itemPercentage
                )

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                typedArray.recycle()
            }
        }

        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)
            item.rect = RectF(width/2.0f - itemSize/2.0f, height/2.0f - itemSize/2.0f, width/2.0f + itemSize/2.0f, height/2.0f + itemSize/2.0f)
        }

        private fun setShadeLocation() {
            val r = itemSize/2.0f + shadeSize/2.0f - (100-itemPercentage)*(itemSize/100)
            val shadeX = item.rect.centerX() + Calculator.cos(shadeRadius) * r
            val shadeY = item.rect.centerY() + Calculator.sin(shadeRadius) * r

            shade.left = shadeX - shadeSize/2f
            shade.top = shadeY - shadeSize/2f
            shade.right = shadeX + shadeSize/2f
            shade.bottom = shadeY + shadeSize/2f
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            item.image.setBounds((width/2 - item.rect.width()/2).toInt(), (height/2 - item.rect.height()/2).toInt(), (width/2 + item.rect.width()/2).toInt(), (height/2 + item.rect.height()/2).toInt())
            item.image.draw(canvas)

            canvas.drawRoundRect(shade,
                sideCornerRadius, sideCornerRadius,
                paintShade
            )
        }

        fun startProgress() {
            progressAnimator = ValueAnimator.ofInt(
                shadeRadius,
                shadeRadius+359
            ).apply {
                duration = itemAnimDuration
                interpolator = LinearInterpolator()
                repeatCount = ValueAnimator.INFINITE
//            repeatMode = ValueAnimator.REVERSE
                addUpdateListener {
                    shadeRadius = it.animatedValue as Int
                }
                start()
            }
        }
        fun stopProgress() {
            progressAnimator?.removeAllListeners()
            progressAnimator?.cancel()
        }

        companion object {
            private const val INVALID_RES = -1
            private const val DEFAULT_SHADE_COLOR = "#2DFFFFFF"
            private const val DEFAULT_ITEM_COLOR = "#FFFFFF00"
            private const val DEFAULT_ANIM_DURATION = 1000L
            private const val DEFAULT_IMAGE_SIZE = 18F
            private const val DEFAULT_CORNER_RADIUS = 0F
            private const val DEFAULT_SHADE_DIRECTION = 0
            fun Context.d2p(dp: Float): Float {
                return (dp * resources.displayMetrics.density).roundToInt().toFloat()
            }
        }
    }