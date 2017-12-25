package com.twinflag.touch.utils;

import com.twinflag.touch.model.Content;
import com.twinflag.touch.model.LevelOne;
import com.twinflag.touch.model.LevelTwo;
import com.twinflag.touch.model.Material;
import lombok.Data;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class XmlGenerator {

    private Set<String> materialUrls;

    public XmlGenerator() {
        this.materialUrls = new HashSet<>();
    }

    public void levelOneElements(List<LevelOne> levelOnes, String path) throws IOException {
        Element rootElement = DocumentHelper.createElement("menu");
        Document document = DocumentHelper.createDocument(rootElement);
        for(LevelOne levelOne : levelOnes) {
            Element levelOneElement = levelOneElement(levelOne);
            rootElement.add(levelOneElement);
        }
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path));
        xmlWriter.write(document);
        xmlWriter.close();
    }

    private Element levelOneElement(LevelOne levelOne) {
        Element level_1 = DocumentHelper.createElement("level-1");
        level_1.addAttribute("iconurl_a", "Res/" + levelOne.getNormalPic().getMacName());
        materialUrls.add(levelOne.getNormalPic().getMacName());
        level_1.addAttribute("iconurl_b", "Res/" + levelOne.getSelectedPic().getMacName());
        materialUrls.add(levelOne.getSelectedPic().getMacName());
        level_1.addAttribute("url", "Res");
        for(LevelTwo levelTwo : levelOne.getLevelTwos()) {
            Element levelTwoElement = levelTwoElement(levelTwo);
            level_1.add(levelTwoElement);
        }
        return level_1;
    }

    private Element levelTwoElement(LevelTwo levelTwo) {
        Element level_2 = DocumentHelper.createElement("level-2");
        level_2.addAttribute("label", levelTwo.getLabel());
        level_2.addAttribute("ismany", "false");
        if (levelTwo.getTitle() != null) {
            level_2.addAttribute("title", levelTwo.getTitle());
        }
        if (levelTwo.getUrl() != null) {
            level_2.addAttribute("url", "Res/" + levelTwo.getUrl().getMacName());
            materialUrls.add(levelTwo.getUrl().getMacName());
        } else {
            List<Content> contents = levelTwo.getContents();
            if (contents != null && contents.size() > 0) {
                for(Content content : contents) {
                    Element contentElement = contentElement(content);
                    level_2.add(contentElement);
                }
            }
        }
        return level_2;
    }

    private Element contentElement(Content content) {
        Element element = DocumentHelper.createElement("content");
        if (content.getLabel() != null) {
            element.addAttribute("label", content.getLabel());
        }
        if (content.getTitle() != null) {
            element.addAttribute("title", content.getTitle());
        } else {
            if (content.getLabel() != null) {
                element.addAttribute("title", content.getLabel());
            }
        }
        List<Material> materials = content.getMaterials();
        if (materials != null && materials.size() > 0) {
            if (materials.size() == 1) {
                element.addAttribute("url", "Res/" + materials.get(0).getMacName());
                materialUrls.add(materials.get(0).getMacName());
            } else {
                for(Material material : materials) {
                    if (content.getType() == 1) {
                        Element manyPic = manyPicElement(material.getMacName());
                        element.add(manyPic);
                    } else if (content.getType() == 2){
                        Element manyTxt = manyTxtElement(material.getMacName());
                        element.add(manyTxt);
                    }
                }
            }
        }
        return element;
    }

    private Element manyPicElement(String url) {
        Element element = DocumentHelper.createElement("manypic");
        element.addAttribute("url", "Res/" + url);
        materialUrls.add(url);
        return element;
    }
    private Element manyTxtElement(String url) {
        Element element = DocumentHelper.createElement("manytxt");
        element.addAttribute("url", "Res/" + url);
        materialUrls.add(url);
        return element;
    }
}
