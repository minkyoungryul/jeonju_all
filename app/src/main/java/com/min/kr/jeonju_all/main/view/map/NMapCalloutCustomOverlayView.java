/*
 * Copyright 2016 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.min.kr.jeonju_all.main.view.map;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.main.view.BookmarkMapActivity;
import com.min.kr.jeonju_all.util.Logger;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.overlay.NMapPOIitem;

public class NMapCalloutCustomOverlayView extends NMapCalloutOverlayView {

	private View mCalloutView;
	private TextView mCalloutText;
	private TextView txtAddress,txttel,txtinfo,txtRoad,txtStreet,txtceo,txtceoo;
	private ImageView imgStore,imgLike,imgRecommand;

	BookmarkMapActivity bookmark;

	public NMapCalloutCustomOverlayView(Context context, NMapOverlay itemOverlay, NMapOverlayItem item, Rect itemBounds, String name,
                                        String address, String tel, String homepage, String url, Double x, Double y, int type) {
		super(context, itemOverlay, item, itemBounds, name, address, tel, homepage, url, x, y, type);

		//Logger.log("#30 datas ->" +datas);

		bookmark = (BookmarkMapActivity)getContext();

		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.callout_overlay_view, this, true);

		mCalloutView = findViewById(R.id.callout_overlay);
		mCalloutText = (TextView)mCalloutView.findViewById(R.id.callout_text);

		mCalloutView = findViewById(R.id.callout_overlay);
		mCalloutText = (TextView)mCalloutView.findViewById(R.id.callout_text);

		txtceo = (TextView)mCalloutView.findViewById(R.id.store_ceo);
		txtceoo = (TextView)mCalloutView.findViewById(R.id.store_ceoo);

		txtAddress = (TextView)mCalloutView.findViewById(R.id.store_address);
		txttel = (TextView)mCalloutView.findViewById(R.id.store_tel);
		txtinfo = (TextView)mCalloutView.findViewById(R.id.store_menu);
		txtRoad = (TextView)mCalloutView.findViewById(R.id.bt_map_search);
		txtStreet = (TextView)mCalloutView.findViewById(R.id.bt_map_street);

		//imgLike = (ImageView)mCalloutView.findViewById(R.id.img_map_like);
		imgStore = (ImageView)mCalloutView.findViewById(R.id.img_store);
		imgRecommand = (ImageView)mCalloutView.findViewById(R.id.img_recommand);

		mCalloutView.setOnClickListener(callOutClickListener);

		txtceo.setText("");
		txtceoo.setText("");
		imgRecommand.setVisibility(GONE);

		mCalloutText.setText(name);
		txtAddress.setText(address);
		txttel.setText(tel);
		txtinfo.setText(homepage);
		Glide.with(getContext())
				.load(url)
				.fitCenter()
				.into(imgStore);


		txtRoad.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Logger.log("#35 kind -> "+kind.datas);

				bookmark.setRoad(name);

			}
		});

		txtStreet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Logger.log("#85 x: "+x+" / y: "+y);
				bookmark.getStreetView(x,y);

			}
		});


		if (item instanceof NMapPOIitem) {
			if (((NMapPOIitem)item).hasRightAccessory() == false) {
				//mRightArrow.setVisibility(View.GONE);
			}
		}
	}

	private final OnClickListener callOutClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			if (mOnClickListener != null) {
				mOnClickListener.onClick(null, mItemOverlay, mOverlayItem);
			}
		}
	};


}
