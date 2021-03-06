package dev.chu.memo.ui.rv_simple.amimation.usage

import android.animation.ValueAnimator
import android.view.View
import dev.chu.memo.ui.rv_simple.amimation.AnimatedItemHolder
import kotlinx.android.synthetic.main.view_post.view.*

class PostViewHolder(view: View) : AnimatedItemHolder(view) {

    private companion object {
        const val ANIMATION_DURATION = 150L
    }

    private var animator: ValueAnimator? = null
    private var isShown = false

    fun bind(post: Post) {
        itemView.apply {
            postTitle.text = post.title
            postBody.text = post.body

            alpha = 0f
            this@PostViewHolder.isShown = false
        }
    }

    override fun onEnterFromTop() {
        startShowAnimation()
    }

    override fun onExitToTop() {
        startExitAnimation()
    }

    override fun onEnterFromBottom() {
        startShowAnimation()
    }

    override fun onExitToBottom() {
        startExitAnimation()
    }

    private fun startExitAnimation() {
        if (isShown) {
            isShown = false
            animator?.cancel()
            animator = ValueAnimator.ofFloat(itemView.alpha, 0f).apply {
                addUpdateListener {
                    itemView.alpha = it.animatedValue as Float
                }
                duration = ANIMATION_DURATION
                start()
            }
        }
    }

    private fun startShowAnimation() {
        if (!isShown) {
            isShown = true
            animator?.cancel()
            animator = ValueAnimator.ofFloat(itemView.alpha, 1f).apply {
                addUpdateListener {
                    itemView.alpha = it.animatedValue as Float
                }
                duration = ANIMATION_DURATION
                start()
            }
        }
    }
}