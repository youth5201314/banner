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

public class FlipVerticalTransformer extends ABaseTransformer {

	@Override
	protected void onTransform(View view, float position) {
		final float rotation = -180f * position;

		view.setAlpha(rotation > 90f || rotation < -90f ? 0f : 1f);
		view.setPivotX(view.getWidth() * 0.5f);
		view.setPivotY(view.getHeight() * 0.5f);
		view.setRotationX(rotation);
	}

	@Override
	protected void onPostTransform(View page, float position) {
		super.onPostTransform(page, position);

		if (position > -0.5f && position < 0.5f) {
			page.setVisibility(View.VISIBLE);
		} else {
			page.setVisibility(View.INVISIBLE);
		}
	}
}
