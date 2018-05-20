package com.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sprhib.model.Team;
import com.sprhib.service.TeamService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;

//CREATE TABLE `teams` ( `id` int(6) NOT NULL AUTO_INCREMENT, `name` varchar(40) NOT NULL, `rating` int(6) NOT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
@Controller
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // REST Service
    @RequestMapping(value = "/journal", produces
            = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<Team> getJournal() {
        List<Team> teams = teamService.getTeams();
        return teams;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addTeamPage() {
        ModelAndView modelAndView = new ModelAndView("add-team-form");
        modelAndView.addObject("team", new Team());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addingTeam(@ModelAttribute Team team) {

        ModelAndView modelAndView = new ModelAndView("home");
        teamService.addTeam(team);

        String message = "Team was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value = "/list")
    public ModelAndView listOfTeams() {
        ModelAndView modelAndView = new ModelAndView("list-of-teams");

        List<Team> teams = teamService.getTeams();
        modelAndView.addObject("teams", teams);

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editTeamPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("edit-team-form");
        Team team = teamService.getTeam(id);
        modelAndView.addObject("team", team);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView edditingTeam(@ModelAttribute Team team, @PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView("home");

        teamService.updateTeam(team);

        String message = "Team was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTeam(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("home");
        teamService.deleteTeam(id);
        String message = "Team was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
