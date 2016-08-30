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

public class ForegroundToBackgroundTransformer extends ABaseTransformer {

	@Override
	protected void onTransform(View view, float position) {
		final float height = view.getHeight();
		final float width = view.getWidth();
		final float scale = min(position > 0 ? 1f : Math.abs(1f + position), 0.5f);

		view.setScaleX(scale);
		view.setScaleY(scale);
		view.setPivotX(width * 0.5f);
		view.setPivotY(height * 0.5f);
		view.setTranslationX(position > 0 ? width * position : -width * position * 0.25f);
	}

}
