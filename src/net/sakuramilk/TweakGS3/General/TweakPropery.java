/*
 * Copyright (C) 2011-2012 sakuramilk <c.sakuramilk@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sakuramilk.TweakGS3.General;

import net.sakuramilk.TweakGS3.Common.PropertyManager;
import net.sakuramilk.TweakGS3.Common.SystemCommand;

public class TweakPropery extends PropertyManager {

    public TweakPropery() {
        super("/data/tweakgs3.prop");

        // if not exists, create prop file.
        if (!mFile.exists()) {
            SystemCommand.touch(mFile.getPath(), "0666");
        }
    }
}
