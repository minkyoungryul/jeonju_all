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
package com.min.kr.jeonju_all.kindFood.view.map;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.kindFood.view.KindFoodMap2Activity;
import com.min.kr.jeonju_all.kindFood.view.KindFoodMapActivity;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.overlay.NMapPOIitem;

public class NMapCalloutCustomOverlayView extends NMapCalloutOverlayView {

	private View mCalloutView;
	private TextView mCalloutText;
	private TextView txtAddress,txtTel,txtCeo,txtMenu,txtPrice,txtRoad,txtStreet,txtNavi;
	private ImageView imgStore,imgLike;
	KindFoodMapActivity kind;
	KindFoodMap2Activity kind2;

	public NMapCalloutCustomOverlayView(Context context, NMapOverlay itemOverlay, NMapOverlayItem item, Rect itemBounds, String ceoName,
										String name, String address, String price, String menu, String tel, String url, Double x, Double y, int type) {
		super(context, itemOverlay, item, itemBounds, ceoName, name, address, price, menu, tel, url, x, y, type);

		//Logger.log("#30 datas ->" +datas);
		if (type == 0){
			kind = (KindFoodMapActivity)getContext();
		}else{
			kind2 = (KindFoodMap2Activity)getContext();
		}

		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li = (LayoutInflater)getContext().getSystemService(infService);
		li.inflate(R.layout.callout_overlay_view, this, true);

		mCalloutView = findViewById(R.id.callout_overlay);
		mCalloutText = (TextView)mCalloutView.findViewById(R.id.callout_text);

		txtAddress = (TextView)mCalloutView.findViewById(R.id.store_address);
		txtTel = (TextView)mCalloutView.findViewById(R.id.store_tel);
		txtCeo = (TextView)mCalloutView.findViewById(R.id.store_ceo);
		txtMenu = (TextView)mCalloutView.findViewById(R.id.store_menu);
		txtPrice = (TextView)mCalloutView.findViewById(R.id.store_price);
		txtRoad = (TextView)mCalloutView.findViewById(R.id.bt_map_search);
		txtStreet = (TextView)mCalloutView.findViewById(R.id.bt_map_street);
		//txtNavi = (TextView)mCalloutView.findViewById(R.id.bt_map_navi);

		//imgLike = (ImageView)mCalloutView.findViewById(R.id.img_map_like);
		imgStore = (ImageView)mCalloutView.findViewById(R.id.img_store);
		//mRightArrow = findViewById(R.id.callout_rightArrow);

		mCalloutView.setOnClickListener(callOutClickListener);

		mCalloutText.setText(name);
		txtAddress.setText(address);
		txtTel.setText(tel);
		txtCeo.setText(ceoName);
		txtMenu.setText(menu);
		txtPrice.setText(price);
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
				}else{
					kind2.setRoad(name);
				}

			}
		});

		txtStreet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (type == 0){
					kind.getStreetView(x,y);
				}else{
					kind2.getStreetView(x,y);
				}

			}
		});


		if (item instanceof NMapPOIitem) {
			if (((NMapPOIitem)item).hasRightAccessory() == false) {
				//mRightArrow.setVisibility(View.GONE);
			}
		}
	}

	private final View.OnClickListener callOutClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View view) {
			if (mOnClickListener != null) {
				mOnClickListener.onClick(null, mItemOverlay, mOverlayItem);
			}
		}
	};


}
