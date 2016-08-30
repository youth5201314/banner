/*
 * Copyright 2014 Toxic Bakery
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.youth.banner.transformer;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;

public class TabletTransformer extends ABaseTransformer {

	private static final Matrix OFFSET_MATRIX = new Matrix();
	private static final Camera OFFSET_CAMERA = new Camera();
	private static final float[] OFFSET_TEMP_FLOAT = new float[2];

	@Override
	protected void onTransform(View view, float position) {
		final float rotation = (position < 0 ? 30f : -30f) * Math.abs(position);

		view.setTranslationX(getOffsetXForRotation(rotation, view.getWidth(), view.getHeight()));
		view.setPivotX(view.getWidth() * 0.5f);
		view.setPivotY(0);
		view.setRotationY(rotation);
	}

	protected static final float getOffsetXForRotation(float degrees, int width, int height) {
		OFFSET_MATRIX.reset();
		OFFSET_CAMERA.save();
		OFFSET_CAMERA.rotateY(Math.abs(degrees));
		OFFSET_CAMERA.getMatrix(OFFSET_MATRIX);
		OFFSET_CAMERA.restore();

		OFFSET_MATRIX.preTranslate(-width * 0.5f, -height * 0.5f);
		OFFSET_MATRIX.postTranslate(width * 0.5f, height * 0.5f);
		OFFSET_TEMP_FLOAT[0] = width;
		OFFSET_TEMP_FLOAT[1] = height;
		OFFSET_MATRIX.mapPoints(OFFSET_TEMP_FLOAT);
		return (width - OFFSET_TEMP_FLOAT[0]) * (degrees > 0.0f ? 1.0f : -1.0f);
	}

}
