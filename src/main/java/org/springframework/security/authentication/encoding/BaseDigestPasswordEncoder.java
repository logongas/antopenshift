/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
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

package org.springframework.security.authentication.encoding;

/**
 * <p>Convenience base for digest password encoders.</p>
 *
 * @author colin sampaleanu
 */
public abstract class BaseDigestPasswordEncoder extends BasePasswordEncoder {
    //~ Instance fields ================================================================================================

    private boolean encodeHashAsBase64 = false;

    //~ Methods ========================================================================================================

    public boolean getEncodeHashAsBase64() {
        return encodeHashAsBase64;
    }

    /**
     * The encoded password is normally returned as Hex (32 char) version of the hash bytes. Setting this
     * property to true will cause the encoded pass to be returned as Base64 text, which will consume 24 characters.
     *
     * @param encodeHashAsBase64 set to true for Base64 output
     */
    public void setEncodeHashAsBase64(boolean encodeHashAsBase64) {
        this.encodeHashAsBase64 = encodeHashAsBase64;
    }
}
