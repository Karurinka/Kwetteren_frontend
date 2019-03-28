import { Component, OnInit } from '@angular/core';
import { KweetService } from "../../services/kweet/kweet.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-kweet-create',
  templateUrl: './kweet-create.component.html',
  styleUrls: ['./kweet-create.component.scss']
})
export class KweetCreateComponent implements OnInit {
  createKweetForm: FormGroup;

  constructor(private kweetService: KweetService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.createKweetForm = this.formBuilder.group({
      content: ['', Validators.required]
    });
  }

  createKweet() {
    this.kweetService.create().subscribe()
  }

  get kweetData() {
      return this.createKweetForm.controls;
  }
}
