package com.zhenia.project.home.web;

import com.zhenia.project.home.domain.Employee;
import com.zhenia.project.home.domain.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yauheniya on 07.10.15.
 */

@Controller (value = "HomeController")
public class HomeController  {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "home";
    }

    @Resource(name="myProperties")
    private Properties myProperties;

    String URL0, URL1, URL2, URL3, URL4, URL5, URL6, URL7, URL8, URL9, URL10, URL11, URL12;

    @PostConstruct
    public void init() {
        URL0 = myProperties.getProperty("url.URL0");
        URL1 = myProperties.getProperty("url.URL1");
        URL2 = myProperties.getProperty("url.URL2");
        URL3 = myProperties.getProperty("url.URL3");
        URL4 = myProperties.getProperty("url.URL4");
        URL5 = myProperties.getProperty("url.URL5");
        URL6 = myProperties.getProperty("url.URL6");
        URL7 = myProperties.getProperty("url.URL7");
        URL8 = myProperties.getProperty("url.URL8");
        URL9 = myProperties.getProperty("url.URL9");
        URL10 = myProperties.getProperty("url.URL10");
        URL11 = myProperties.getProperty("url.URL11");
        URL12 = myProperties.getProperty("url.URL12");
    }

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/voucherList")
    public ModelAndView getAllVouchers() {
        List<LinkedHashMap> voucherdto = restTemplate.getForObject(URL0+URL1, List.class);

        return new ModelAndView("voucherList", "voucherdto", voucherdto);

    }

    @RequestMapping("/inputVoucher")
    public ModelAndView formVoucher() {

        return new ModelAndView("inputVoucher", "voucher", new Voucher ());
    }

    @RequestMapping("/voucherById")
    @ResponseBody
    public ModelAndView getVoucherById(@RequestParam ("vouchId") Integer vouchId,Model model) {
        model.addAttribute("vouchId", vouchId);
        Voucher onevoucher = restTemplate.getForObject(URL0+URL2, Voucher.class, vouchId);
        return new ModelAndView("editVoucher", "onevoucher", onevoucher);
    }

    @RequestMapping(value = "/update/voucher/{vouchId}/{country}/{price}/{discaunt}")
    @ResponseBody
    public String updateVoucher(@PathVariable Integer vouchId, @PathVariable String country,
                                @PathVariable Integer price, @PathVariable Integer discaunt) {
        Voucher voucher = new Voucher();
        voucher.setVouchId(vouchId);
        voucher.setCountry(country);
        voucher.setPrice(price);
        voucher.setDiscaunt(discaunt);
        restTemplate.put(URL0+URL3, voucher);
        return "redirect:/voucherList";

    }

    @RequestMapping(value = "/delete/voucher/{vouchId}")
    @ResponseBody
    private String deleteVoucher(@PathVariable Integer vouchId) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("vouchId", vouchId);
        restTemplate.delete(URL0+URL4, params);
        return "redirect:/voucherList";
    }

    @RequestMapping(value = "/voucher/insert/{country}/{price}/{discaunt}")
    @ResponseBody
    public  String addVoucher(@PathVariable String country, @PathVariable Integer price,
                              @PathVariable Integer discaunt) {
        Voucher voucher = new Voucher();
        voucher.setCountry(country);
        voucher.setPrice(price);
        voucher.setDiscaunt(discaunt);
        restTemplate.postForObject(URL0+URL5, voucher, Voucher.class);
        return "redirect:/voucherList";

    }

    @RequestMapping("/costList")
    public ModelAndView getAllCost() {
        List<LinkedHashMap> cost = restTemplate.getForObject(URL0+URL6, List.class);

        return new ModelAndView("costList", "cost", cost);
    }

    @RequestMapping("/employeeList")
    public ModelAndView getDtoEmployees() {
        Map<String, Object>  dto = restTemplate.getForObject(URL0+URL7, Map.class);

        return new ModelAndView("employeeList", "dto", dto);
    }

    @RequestMapping("/inputEmployee")
    public ModelAndView formEmployee() {

        return new ModelAndView("inputEmployee", "employee", new Employee());
    }

    @RequestMapping(value = "/employee/insert/{name}/{surname}/{idVoucher}/{date}")
    @ResponseBody
    public Employee addEmployee(@PathVariable String name, @PathVariable String surname,
                              @PathVariable Integer idVoucher, @PathVariable String date) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setIdVoucher(idVoucher);
        Date vacation = getDOB(date);
        employee.setVacation(vacation);
        employee = restTemplate.postForObject(URL0+URL8, employee, Employee.class);
        return employee;
    }

    @RequestMapping(value = "/delete/employee/{emplId}")
    @ResponseBody
    public String deleteEmployee(@PathVariable Integer emplId) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("emplId", emplId);
        restTemplate.delete(URL0+URL9, params );
        return "redirect:/employeeList";
    }

    @RequestMapping(value = "/update/employee/{emplId}/{name}/{surname}/{idVoucher}/{date}")
    @ResponseBody
    public String updateEmployee(@PathVariable Integer emplId,@PathVariable String name, @PathVariable String surname,
                                 @PathVariable Integer idVoucher, @PathVariable String date)  {
        Employee employee = new Employee();
        employee.setEmplId(emplId);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setIdVoucher(idVoucher);
        Date vacation = getDOB(date);
        employee.setVacation(vacation);
        restTemplate.put(URL0+URL10, employee);
        return "redirect:/employeeList";
    }

    @RequestMapping("/employeeById")
    @ResponseBody
    public ModelAndView EmployeeById(@RequestParam ("emplId") Integer emplId,Model model) {
        model.addAttribute("emplId",emplId);
        Employee oneemployee = restTemplate.getForObject(URL0+URL11, Employee.class, emplId);
        return new ModelAndView("editEmployee", "oneemployee", oneemployee);
    }

    @RequestMapping(value = "/searchVacation")
    ModelAndView searchVacation(@RequestParam("vacation") String vacation,
                                @RequestParam("vacation2") String vacation2,Model model){
        model.addAttribute("vacation",vacation);
        model.addAttribute("vacation2", vacation2);
        List<LinkedHashMap> search = restTemplate.getForObject(URL0+URL12, List.class, vacation, vacation2);
        return new ModelAndView("searchVacation", "search", search);

    }

    public static Date getDOB(String day) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}