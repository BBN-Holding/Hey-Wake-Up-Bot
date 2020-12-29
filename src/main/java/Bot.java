/*
 * Copyright 2020 GregTCLTK and Schlauer-Hax
 *
 * Licensed under the GNU Affero General Public License, Version 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.gnu.org/licenses/agpl-3.0.en.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import audio.AudioManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import javax.security.auth.login.LoginException;

public class Bot {

    public static void main(String[] args) {
        for (String token : SECRETS.Tokens) {
            try {
                JDABuilder.createDefault(token).setStatus(OnlineStatus.INVISIBLE).addEventListeners(new WakeUpListener()).build();
            } catch (LoginException e) {
                e.printStackTrace();
            }
        }
    }
}
