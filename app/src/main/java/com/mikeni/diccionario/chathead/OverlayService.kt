package com.mikeni.diccionario.chathead

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.*
import android.widget.ImageView
import com.mikeni.diccionario.R
import com.mikeni.diccionario.view.MainActivity

class OverlayService : Service() {
    private var mWindowManager: WindowManager? = null
    private var mChatHeadView: View? = null
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate() {
        super.onCreate()
        //Inflate the chat head layout we created
        mChatHeadView = LayoutInflater.from(this).inflate(R.layout.layout_chat_head, null)

        val params = if (Build.VERSION.SDK_INT >= 26) {
            WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            )
        } else {
            //Add the view to the window.
            WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            )
        }

        //Specify the chat head position
        //Initially view will be added to top-left corner
        params.apply {
            gravity = Gravity.TOP or Gravity.START
            x = 0
            y = 100
        }

        //Add the view to the window
        mWindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mWindowManager!!.addView(mChatHeadView, params)

        //Set the close button.
        val closeButton = mChatHeadView?.findViewById<ImageView>(R.id.close_btn)

        closeButton?.setOnClickListener { //close the service and remove the chat head from the window
            stopSelf()
        }

        //Drag and move chat head using user's touch action.
        val chatHeadImage = mChatHeadView?.findViewById<View>(R.id.img_bubble) as ImageView

        chatHeadImage.setOnTouchListener(object : View.OnTouchListener {
            private var lastAction = 0
            private var initialX = 0
            private var initialY = 0
            private var initialTouchX = 0f
            private var initialTouchY = 0f
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {

                        //remember the initial position.
                        initialX = params.x
                        initialY = params.y

                        //get the touch location
                        initialTouchX = event.rawX
                        initialTouchY = event.rawY
                        lastAction = event.action
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        //As we implemented on touch listener with ACTION_MOVE,
                        //we have to check if the previous action was ACTION_DOWN
                        //to identify if the user clicked the view or not.
                        if (lastAction == MotionEvent.ACTION_DOWN) {
                            //Open the chat conversation click.
                            val intent =
                                Intent(this@OverlayService, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)

                            //close the service and remove the chat heads
                            //stopSelf()
                        }
                        lastAction = event.action
                        return true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (event.rawX - initialTouchX).toInt()
                        params.y = initialY + (event.rawY - initialTouchY).toInt()

                        //Update the layout with new X & Y coordinate
                        mWindowManager!!.updateViewLayout(mChatHeadView, params)
                        lastAction = event.action
                        return true
                    }
                }
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mChatHeadView != null) mWindowManager!!.removeView(mChatHeadView)
    }
}