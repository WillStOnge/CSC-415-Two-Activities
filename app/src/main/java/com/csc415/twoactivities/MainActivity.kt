package com.csc415.twoactivities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.content.Intent
import android.widget.EditText
import android.widget.TextView

const val EXTRA_MESSAGE = "com.csc415.twoactivities.extra.MESSAGE"
const val TEXT_REQUEST = 1

class MainActivity : AppCompatActivity()
{
	private val logTag = MainActivity::class.java.simpleName
	private lateinit var mMessageEditText: EditText
	private lateinit var mReplyHeadTextView: TextView
	private lateinit var mReplyTextView: TextView


	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		mMessageEditText = findViewById(R.id.editText_main);
		mReplyHeadTextView = findViewById(R.id.text_header_reply);
		mReplyTextView = findViewById(R.id.text_message_reply);
	}

	fun launchSecondActivity(view: View)
	{
		Log.d(logTag, "Button clicked!");
		val intent = Intent(this, SecondActivity::class.java)
		intent.putExtra(EXTRA_MESSAGE, mMessageEditText.text.toString());
		startActivityForResult(intent, TEXT_REQUEST)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
	{
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == TEXT_REQUEST)
		{
			if (resultCode == RESULT_OK)
			{
				mReplyHeadTextView.visibility = View.VISIBLE
				mReplyTextView.text = data!!.getStringExtra(EXTRA_REPLY)
				mReplyTextView.visibility = View.VISIBLE
			}
		}
	}
}