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
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class WakeUpListener extends ListenerAdapter {

    private AudioManager audioManager;

    public WakeUpListener(AudioManager audioManager) {
        this.audioManager = audioManager;
    }
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals("401817301919465482") || event.getAuthor().getId().equals("261083609148948488")) {
            if (event.getMessage().getContentRaw().toLowerCase().startsWith("wake up")) {
                if (event.getMessage().getMentionedUsers().size() == 1) {
                    event.getJDA().getGuildById("757966278936756345").getAudioManager().openAudioConnection(event.getJDA().getGuildById("757966278936756345").getMember(event.getMessage().getMentionedUsers().get(0)).getVoiceState().getChannel());
                    audioManager.loadTrack(event);
                } else {
                    event.getTextChannel().sendMessage("Wrong arguments. Example: `wake up @Skidder`").queue();
                }
            }
        }
    }
}
