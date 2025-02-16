package app.story.service;

import app.story.model.Story;
import app.story.repository.StoryRepository;
import app.user.model.User;
import app.web.dto.StoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public void addStory(StoryRequest storyRequest, User user) {

        Story story = Story.builder()
                .title(storyRequest.getTitle())
                .description(storyRequest.getDescription())
                .kind(storyRequest.getKind())
                .date(storyRequest.getDate())
                .addedOn(LocalDate.now())
                .owner(user)
                .isVisible(false)
                .build();

        storyRepository.save(story);
    }

    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }
}
