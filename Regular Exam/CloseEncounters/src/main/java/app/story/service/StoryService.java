package app.story.service;

import app.story.model.Story;
import app.story.repository.StoryRepository;
import app.user.model.User;
import app.web.dto.StoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    public List<Story> getSharedStories() {
        return storyRepository.findAllByIsVisibleTrue();
    }

    public void shareStory(UUID storyId) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid story Id:" + storyId));
        story.setVisible(true);
        storyRepository.save(story);
    }

    public Story getStoryById(UUID id) {
        return storyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Story not found for id: " + id));
    }

    public void deleteStoryById(UUID id) {
        storyRepository.deleteById(id);
    }
}
