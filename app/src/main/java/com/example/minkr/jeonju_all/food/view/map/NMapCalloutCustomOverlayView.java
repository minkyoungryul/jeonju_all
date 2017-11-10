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
package com.example.minkr.jeonju_all.food.view.map;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.food.view.FoodMap2Activity;
import com.example.minkr.jeonju_all.food.view.FoodMap3Activity;
import com.example.minkr.jeonju_all.food.view.FoodMapActivity;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.overlay.NMapPOIitem;

public class NMapCalloutCustomOverlayView extends NMapCalloutOverlayView {

	private View mCalloutView;
	private TextView mCalloutText;
	private TextView txtAddress,txtTel,txtHoliday,txtClose,txtMenu,txtRoad,txtStreet;
	private ImageView imgStore,imgLike;
	FoodMapActivity kind;
	FoodMap2Activity kind2;
	FoodMap3Activity kind3;

	public NMapCalloutCustomOverlayView(Context context, NMapOverlay itemOverlay, NMapOverlayItem item, Rect itemBounds, String name,
                                        String address, String tel, String holiday, String close, String menu, String url, Double x, Double y, int type) {
		super(context, itemOverlay, item, itemBounds, name, address, tel, holiday, close, menu, url, x, y, type);

		//Logger.log("#30 datas ->" +datas);

		if (type == 0){
			kind = (FoodMapActivity)getContext();
		}else if (type == 1){
			kind2 = (FoodMap2Activity)getContext();
		}else{
			kind3 = (FoodMap3Activity)getContext();
		}


		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.callout_overlay_food_view, this, true);

		mCalloutView = findViewById(R.id.callout_overlay);
		mCalloutText = (TextView)mCalloutView.findViewById(R.id.callout_text);

		txtAddress = (TextView)mCalloutView.findViewById(R.id.store_address);
		txtTel = (TextView)mCalloutView.findViewById(R.id.store_tel);
		txtHoliday = (TextView)mCalloutView.findViewById(R.id.txtHoliday);
		txtClose = (TextView)mCalloutView.findViewById(R.id.txtClose);
		txtMenu = (TextView)mCalloutView.findViewById(R.id.store_menu);
		txtRoad = (TextView)mCalloutView.findViewById(R.id.bt_map_search);
		txtStreet = (TextView)mCalloutView.findViewById(R.id.bt_map_street);

		imgLike = (ImageView)mCalloutView.findViewById(R.id.img_map_like);
		imgStore = (ImageView)mCalloutView.findViewById(R.id.img_store);

		mCalloutView.setOnClickListener(callOutClickListener);

		mCalloutText.setText(name);
		txtAddress.setText(address);
		txtTel.setText(tel);
		txtHoliday.setText(holiday);
		txtClose.setText(close);
		txtMenu.setText(menu);
		Glide.with(getContext())
				.load(url)
				.fitCenter()
				.into(imgStore);


		txtRoad.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Logger.log("#35 kind -> "+kind.datas);

				if (type == 0) {
					kind.setRoad(name);
				}else if (type == 1){
					kind2.setRoad(name);
				}else{
					kind3.setRoad(name);
				}

			}
		});

		txtStreet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (type == 0){
					kind.getStreetView(x,y);
				}else if (type == 1){
					kind2.getStreetView(x,y);
				}else{
					kind3.getStreetView(x,y);
				}

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
