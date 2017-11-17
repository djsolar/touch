package com.twinflag.touch.utils;

import com.twinflag.touch.entity.Content;
import com.twinflag.touch.entity.LevelOneContent;
import com.twinflag.touch.entity.LevelTwoContent;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {

    public List<LevelOneContent> parseMenu(String path) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(path);
        Element root = document.getRootElement();

        List<LevelOneContent> levelOneContents = new ArrayList<>();
        List<Element> levelOneElements = root.elements("level-1");
        for(Element element : levelOneElements) {
            levelOneContents.add(parseLevelOne(element));
        }
        return levelOneContents;
    }

    private LevelOneContent parseLevelOne(Element node) {
        LevelOneContent levelOneContent = new LevelOneContent();
        String normalPic = node.attributeValue("iconurl_a");
        String selectedPic = node.attributeValue("iconurl_b");
        String url = node.attributeValue("url");
        levelOneContent.setNormalPic(normalPic);
        levelOneContent.setSelectedPic(selectedPic);
        levelOneContent.setUrl(url);

        List<Element> elements = node.elements("level-2");
        List<LevelTwoContent> levelTwoContents = new ArrayList<>();
        for(Element element : elements) {
            LevelTwoContent levelTwoContent = parseLevelTwo(element);
            levelTwoContents.add(levelTwoContent);
        }
        levelOneContent.setTwoContent(levelTwoContents);
        return levelOneContent;
    }

    private LevelTwoContent parseLevelTwo(Element node) {
        LevelTwoContent levelTwoContent = new LevelTwoContent();
        String label = node.attributeValue("label");
        boolean isMany = Boolean.parseBoolean(node.attributeValue("ismany"));
        levelTwoContent.setLabel(label);
        levelTwoContent.setMany(isMany);

        List<Element> elements = node.elements("content");
        if (elements == null || elements.size() == 0) {
            String url = node.attributeValue("url");
            if (url != null) {
                levelTwoContent.setUrl(url);
            }
            String title = node.attributeValue("title");
            if (title != null) {
                levelTwoContent.setTitle(title);
            }
        } else {
            List<Content> contents = new ArrayList<>();
            for(Element element : elements) {
                Content content = parseContent(element);
                if (content == null)
                    continue;
                contents.add(content);
            }
            levelTwoContent.setContents(contents);
        }
        return levelTwoContent;
    }

    private Content parseContent(Element node) {
        String url = node.attributeValue("url");
        String label = node.attributeValue("label");
        String title = node.attributeValue("title");
        if (url != null && label != null && title != null) {
            Content content = new Content();
            content.setLabel(label);
            content.setTitle(title);
            List<String> paths = new ArrayList<>();
            paths.add(url);
            content.setType(getType(url));
            content.setPaths(paths);
            return content;
        }
        if (url == null) {
            Content content = new Content();
            content.setLabel(label);
            content.setTitle(title);
            List<Element> manyPics = node.elements("manypic");
            List<String> paths = getPicPaths(manyPics);
            if (paths != null) {
                content.setPaths(paths);
                content.setType(SourceType.IMAGE.getType());
            }
            List<Element> manyTxts = node.elements("manytxt");
            paths = getPicPaths(manyTxts);
            if (paths != null) {
                content.setPaths(paths);
                content.setType(SourceType.TEXT.getType());
            }
            return content;
        }

        if (label == null && title == null) {
            Content content = new Content();
            List<String> paths = new ArrayList<>();
            paths.add(url);
            content.setPaths(paths);
            content.setType(SourceType.IMAGE.getType());
            return content;
        }
        return null;
    }

    private List<String> getPicPaths(List<Element> elements) {
        if (elements != null && elements.size() > 0) {
            List<String> paths = new ArrayList<>();
            for(Element element : elements) {
                String picUrl = element.attributeValue("url");
                paths.add(picUrl);
            }
            return paths;
        }
        return null;
    }


    private int getType(String path) {
        if (path.endsWith(SourceType.TEXT.getSuffix())) {
            return SourceType.TEXT.getType();
        }
        if (path.endsWith(SourceType.IMAGE.getSuffix())) {
            return SourceType.IMAGE.getType();
        }
        return SourceType.NONE.getType();
    }


    public static void main(String[] args) throws DocumentException {
        ParseUtils parseUtils = new ParseUtils();
        List<LevelOneContent> levelOneContents = parseUtils.parseMenu("C:\\Users\\zhouyiran\\IdeaProjects\\touch\\src\\main\\resources\\StreamingAssets\\menu.xml");
        for(LevelOneContent llc : levelOneContents) {
            List<LevelTwoContent> levelTwoContents = llc.getTwoContent();
            System.out.println(levelTwoContents.size());
        }
    }
}