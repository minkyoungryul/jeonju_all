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
package com.min.kr.jeonju_all.house.view.map;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.facility.view.HealthMapActivity;
import com.min.kr.jeonju_all.facility.view.HospitalMapActivity;
import com.min.kr.jeonju_all.facility.view.MedicineMapActivity;
import com.min.kr.jeonju_all.facility.view.ParkMapActivity;
import com.min.kr.jeonju_all.facility.view.PoliceMapActivity;
import com.min.kr.jeonju_all.house.view.HouseMap2Activity;
import com.min.kr.jeonju_all.house.view.HouseMapActivity;
import com.min.kr.jeonju_all.parking.view.ParkingMapActivity;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.overlay.NMapPOIitem;

public class NMapCalloutCustomOverlayView extends NMapCalloutOverlayView {

	private View mCalloutView;
	private TextView mCalloutText;
	private TextView txtAddress,txtTel,txtinfo,txtRoad,txtStreet,txtceo,txtceoo,txtStorePrice;
	private ImageView imgStore,imgLike,imgRecommand;

	HouseMapActivity house;
	HouseMap2Activity house2;
	PoliceMapActivity police;
	MedicineMapActivity medicine;
	ParkMapActivity park;
	ParkingMapActivity parking;
	HealthMapActivity health;
	HospitalMapActivity hospital;

	public NMapCalloutCustomOverlayView(Context context, NMapOverlay itemOverlay, NMapOverlayItem item, Rect itemBounds, String name,
                                        String address, String tel, String info, String url, Double x, Double y, int type) {
		super(context, itemOverlay, item, itemBounds, name, address, tel, info, url, x, y, type);

		//Logger.log("#30 datas ->" +datas);

		if (type == 0){
			house = (HouseMapActivity) getContext();
		}else if (type == 1){
			house2 = (HouseMap2Activity) getContext();
		}else if (type == 10){
			police = (PoliceMapActivity)getContext();
		}else if (type == 20){
			medicine = (MedicineMapActivity)getContext();
		}else if (type == 30){
			park = (ParkMapActivity)getContext();
		}else if (type == 40){
			parking = (ParkingMapActivity)getContext();
		}else if (type == 50){
			health = (HealthMapActivity)getContext();
		}else{
			hospital = (HospitalMapActivity)getContext();
		}


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
		txtTel = (TextView)mCalloutView.findViewById(R.id.store_tel);
		txtinfo = (TextView)mCalloutView.findViewById(R.id.store_menu);
		txtRoad = (TextView)mCalloutView.findViewById(R.id.bt_map_search);
		txtStreet = (TextView)mCalloutView.findViewById(R.id.bt_map_street);
		txtStorePrice = (TextView)mCalloutView.findViewById(R.id.store_price);

		//imgLike = (ImageView)mCalloutView.findViewById(R.id.img_map_like);
		imgStore = (ImageView)mCalloutView.findViewById(R.id.img_store);
		imgRecommand = (ImageView)mCalloutView.findViewById(R.id.img_recommand);

		mCalloutView.setOnClickListener(callOutClickListener);

		txtceo.setText("");
		txtceoo.setText("");
		imgRecommand.setVisibility(GONE);

		mCalloutText.setText(name);
		txtAddress.setText(address);
		txtTel.setText(tel);
		txtinfo.setText(info);

		if (type == 10 || type == 20 || type == 30 || type == 40 || type == 50 || type == 60){
			imgStore.setVisibility(GONE);
			txtStorePrice.setVisibility(GONE);
		}else {
			Glide.with(getContext())
					.load(url)
					.fitCenter()
					.into(imgStore);
		}

		txtRoad.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Logger.log("#35 kind -> "+kind.datas);

				if (type == 0) {
					house.setRoad(name);
				}else if (type == 1){
					house2.setRoad(name);
				}else if (type == 10){
					police.setRoad(name);
				}else if (type == 20){
					medicine.setRoad(name);
				}else if (type == 30){
					park.setRoad(name);
				}else if (type == 40){
					parking.setRoad(name);
				}else if (type == 50){
					health.setRoad(name);
				}else{
					hospital.setRoad(name);
				}


			}
		});

		txtStreet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (type == 0){
					house.getStreetView(x,y);
				}else if (type == 0){
					house2.getStreetView(x,y);
				}else if (type == 10){
					police.getStreetView(y,x);
				}else if (type == 20){
					medicine.getStreetView(y,x);
				}else if (type == 30){
					park.getStreetView(x,y);
				}else if (type == 40){
					parking.getStreetView(y,x);
				}else if (type == 50){
					health.getStreetView(y,x);
				}else{
					hospital.getStreetView(y,x);
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
