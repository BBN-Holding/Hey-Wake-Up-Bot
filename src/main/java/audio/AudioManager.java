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

package audio;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {


    private final AudioPlayerManager myManager = new DefaultAudioPlayerManager();
    private AudioPlayer audioPlayer;
    private TrackManager trackManager;

    public AudioManager(Guild guild) {
        AudioSourceManagers.registerRemoteSources(myManager);
        myManager.getConfiguration().setFilterHotSwapEnabled(true);

        audioPlayer = myManager.createPlayer();
        trackManager = new TrackManager(audioPlayer, this);
        audioPlayer.addListener(trackManager);
        guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(audioPlayer));
    }

    public AudioPlayerManager getManager() {
        return myManager;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public TrackManager getTrackManager() {
        return trackManager;
    }

    public void loadTrack(MessageReceivedEvent event) {
        Guild guild = event.getGuild();

        myManager.loadItemOrdered(guild, "https://www.youtube.com/watch?v=y90yaLFoYoA", new AudioLoadResultHandler() {

            @Override
            public void trackLoaded(AudioTrack track) {
                trackManager.queue(track, event.getMember());
                audioPlayer.setVolume(400);
                event.getTextChannel().sendMessage("Wake up in progress!").queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {}

            @Override
            public void noMatches() {}

            @Override
            public void loadFailed(FriendlyException e) {}
        });
    }
}
