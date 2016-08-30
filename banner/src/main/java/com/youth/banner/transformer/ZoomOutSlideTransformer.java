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

import android.view.View;

public class ZoomOutSlideTransformer extends ABaseTransformer {

	private static final float MIN_SCALE = 0.85f;
	private static final float MIN_ALPHA = 0.5f;

	@Override
	protected void onTransform(View view, float position) {
		if (position >= -1 || position <= 1) {
			// Modify the default slide transition to shrink the page as well
			final float height = view.getHeight();
			final float width = view.getWidth();
			final float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
			final float vertMargin = height * (1 - scaleFactor) / 2;
			final float horzMargin = width * (1 - scaleFactor) / 2;

			// Center vertically
			view.setPivotY(0.5f * height);
			view.setPivotX(0.5f * width);

			if (position < 0) {
				view.setTranslationX(horzMargin - vertMargin / 2);
			} else {
				view.setTranslationX(-horzMargin + vertMargin / 2);
			}

			// Scale the page down (between MIN_SCALE and 1)
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);

			// Fade the page relative to its size.
			view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
		}
	}

}
